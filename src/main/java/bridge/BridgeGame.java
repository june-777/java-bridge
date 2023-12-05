package bridge;

import bridge.domain.BridgeGameStatus;
import bridge.domain.MoveCommand;
import bridge.domain.MoveResult;
import bridge.domain.MovingStatus;
import java.util.Collections;
import java.util.List;

public class BridgeGame {

    private final List<String> bridge;
    private int tryCount = 1;
    private int nextPosition;

    public BridgeGame(List<String> bridge) {
        this.bridge = bridge;
    }

    public BridgeGameStatus getStatus() {
        if (isEndOfBridge()) {
            return BridgeGameStatus.FINISH;
        }
        return BridgeGameStatus.ING;
    }

    public MoveResult move(MoveCommand moveCommand) {
        if (!isEndOfBridge() && canGoNextTile(moveCommand)) {
            nextPosition++;
            return new MoveResult(MovingStatus.SUCCESS, getCurrentPosition(), tryCount);
        }
        MoveResult moveResult = new MoveResult(MovingStatus.FAIL, nextPosition, tryCount);
        retry();
        return moveResult;
    }

    private boolean isEndOfBridge() {
        return nextPosition == bridge.size();
    }

    private boolean canGoNextTile(MoveCommand moveCommand) {
        String tile = getNextTile();
        String command = moveCommand.getCommand();
        return tile.equals(command);
    }

    private void retry() {
        nextPosition = 0;
        tryCount++;
    }

    private String getNextTile() {
        return bridge.get(nextPosition);
    }

    private int getCurrentPosition() {
        return nextPosition - 1;
    }

    public List<String> getBridge() {
        return Collections.unmodifiableList(bridge);
    }
}
