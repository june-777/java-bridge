package bridge.controller;

import bridge.BridgeGame;
import bridge.domain.MoveCommand;
import bridge.domain.MoveResult;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeController {
    private final InputView inputView;
    private final OutputView outputView;

    public BridgeController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public MoveResult process(final BridgeGame bridgeGame) {
        MoveCommand moveCommand = ExceptionHandler.getOrRetry(this::initMoveCommand);

        MoveResult moveResult = bridgeGame.move(moveCommand);
        renderingMoveResult(bridgeGame.getBridge(), moveResult);

        return moveResult;
    }

    private MoveCommand initMoveCommand() {
        String movingInput = inputView.readMoving();
        return MoveCommand.of(movingInput);
    }

    private void renderingMoveResult(List<String> bridge, MoveResult moveResult) {
        outputView.printMap(bridge, moveResult.currentPosition(), moveResult.movingStatus());
    }

    public void renderingFinalResult(final BridgeGame bridgeGame, final MoveResult moveResult) {
        outputView.printResult(bridgeGame.getBridge(), moveResult, moveResult.tryCount());
    }
}
