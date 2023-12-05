package bridge;

import bridge.domain.BridgeGameStatus;
import bridge.domain.MoveCommand;
import bridge.domain.MoveResult;
import java.util.Collections;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final List<String> bridge;
    private int tryCount = 1;
    private int nextPosition;

    public BridgeGame(List<String> bridge) {
        System.out.println("bridge = " + bridge); // TODO: 출력 제거할 것
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
            return new MoveResult(BridgeGameStatus.SUCCESS, getCurrentPosition());
        }
        MoveResult moveResult = new MoveResult(BridgeGameStatus.FAIL, nextPosition);
        retry(moveCommand);
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

    private void retry(MoveCommand moveCommand) {
        nextPosition = 0;
        tryCount++;
    }

    private String getNextTile() {
        return bridge.get(nextPosition);
    }

    public int getCurrentPosition() {
        return nextPosition - 1;
    }

    public int getTryCount() {
        return tryCount;
    }

    public List<String> getBridge() {
        return Collections.unmodifiableList(bridge);
    }
}
