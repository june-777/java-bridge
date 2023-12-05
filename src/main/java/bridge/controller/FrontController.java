package bridge.controller;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.domain.BridgeGameStatus;
import bridge.domain.GameCommand;
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
        while (bridgeGame.getStatus() == BridgeGameStatus.ING) {
            BridgeGameStatus status = bridgeController.process(bridgeGame);

            if (status == BridgeGameStatus.FAIL) {
                GameCommand gameCommand = ExceptionHandler.getOrRetry(this::initGameCommand);
                if (gameCommand == GameCommand.Q) {
                    break;
                }
            }
        }
    }

    private GameCommand initGameCommand() {
        String gameCommandInput = inputView.readGameCommand();
        return GameCommand.of(gameCommandInput);
    }
}
