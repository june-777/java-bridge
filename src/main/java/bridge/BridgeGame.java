package bridge;

import bridge.domain.BridgeGameStatus;
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

    private boolean isEndOfBridge() {
        return nextPosition == bridge.size();
    }

    public List<String> getBridge() {
        return Collections.unmodifiableList(bridge);
    }
}
