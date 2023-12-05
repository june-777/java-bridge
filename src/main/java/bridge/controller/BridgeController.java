package bridge.controller;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.domain.BridgeGameStatus;
import bridge.domain.GameCommand;
import bridge.domain.MoveCommand;
import bridge.domain.MoveResult;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeMaker bridgeMaker;

    public BridgeController(InputView inputView, OutputView outputView, BridgeMaker bridgeMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeMaker = bridgeMaker;
    }

    public void process() {
        outputView.printStart();
        BridgeGame bridgeGame = ExceptionHandler.getOrRetry(this::initBridgeGame);

        while (bridgeGame.getStatus() == BridgeGameStatus.ING) {
            MoveCommand moveCommand = ExceptionHandler.getOrRetry(this::initMoveCommand);

            MoveResult moveResult = bridgeGame.move(moveCommand);
            BridgeGameStatus status = moveResult.bridgeGameStatus();
            int currentPosition = moveResult.currentPosition();
            List<String> bridge = bridgeGame.getBridge();
            outputView.printMap(
                    bridge,
                    currentPosition,
                    status
            );
            if (status == BridgeGameStatus.FAIL) {
                GameCommand gameCommand = ExceptionHandler.getOrRetry(this::initGameCommand);
                if (gameCommand == GameCommand.Q) {
                    break;
                }
            }
        }
    }

    private BridgeGame initBridgeGame() {
        int bridgeSizeInput = inputView.readBridgeSize();
        List<String> bridge = bridgeMaker.makeBridge(bridgeSizeInput);
        return new BridgeGame(bridge);
    }

    private MoveCommand initMoveCommand() {
        String movingInput = inputView.readMoving();
        return MoveCommand.of(movingInput);
    }

    private GameCommand initGameCommand() {
        String gameCommandInput = inputView.readGameCommand();
        return GameCommand.of(gameCommandInput);
    }

}
