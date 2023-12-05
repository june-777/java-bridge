package bridge.controller;

import bridge.domain.bridge.BridgeGame;
import bridge.domain.bridge.BridgeGameStatus;
import bridge.domain.bridge.BridgeMaker;
import bridge.domain.moving.MoveResult;
import bridge.domain.moving.MovingStatus;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class FrontController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeController bridgeController;
    private final BridgeMaker bridgeMaker;

    public FrontController(InputView inputView, OutputView outputView, BridgeController bridgeController,
                           BridgeMaker bridgeMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeController = bridgeController;
        this.bridgeMaker = bridgeMaker;
    }

    public void process() {
        outputView.printStart();
        BridgeGame bridgeGame = ExceptionHandler.getOrRetry(this::initBridgeGame);

        startBridge(bridgeGame);
    }

    private BridgeGame initBridgeGame() {
        int bridgeSizeInput = inputView.readBridgeSize();
        List<String> bridge = bridgeMaker.makeBridge(bridgeSizeInput);
        return new BridgeGame(bridge);
    }

    private void startBridge(final BridgeGame bridgeGame) {
        ApplicationStatus applicationStatus = ApplicationStatus.RUNNING;
        while (true) {
            MoveResult moveResult = bridgeController.process(bridgeGame);
            applicationStatus = updateApplicationStatus(moveResult, applicationStatus);

            if (isEnd(applicationStatus, bridgeGame.getStatus())) {
                bridgeController.renderingFinalResult(bridgeGame, moveResult);
                break;
            }
        }
    }

    private ApplicationStatus updateApplicationStatus(MoveResult moveResult, ApplicationStatus applicationStatus) {
        if (moveResult.movingStatus() == MovingStatus.FAIL) {
            applicationStatus = ExceptionHandler.getOrRetry(this::initApplicationStatus);
        }
        return applicationStatus;
    }

    private ApplicationStatus initApplicationStatus() {
        String gameCommandInput = inputView.readGameCommand();
        return ApplicationStatus.of(gameCommandInput);
    }

    private boolean isEnd(final ApplicationStatus applicationStatus, final BridgeGameStatus bridgeGameStatus) {
        return applicationStatus == ApplicationStatus.QUIT || bridgeGameStatus == BridgeGameStatus.FINISH;
    }
}
