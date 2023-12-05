package bridge.view;

import bridge.domain.BridgeGameStatus;
import java.util.List;
import java.util.StringJoiner;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final OutputView outputView = new OutputView();

    private OutputView() {
    }

    public static OutputView getOutputView() {
        return outputView;
    }

    public void printStart() {
        System.out.println(Message.START.message);
        System.out.println();
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    // TODO: 메서드 분리, 상수처리 필요
    public void printMap(List<String> bridge, int currentPosition, BridgeGameStatus result) {
        StringJoiner upBridgeFormat = new StringJoiner(" | ", "[ ", " ]");
        StringJoiner downBridgeFormat = new StringJoiner(" | ", "[ ", " ]");
        for (int position = 0; position < currentPosition; position++) {
            String tile = bridge.get(position);
            if (tile.equals("U")) {
                upBridgeFormat.add("O");
                downBridgeFormat.add(" ");
                continue;
            }
            upBridgeFormat.add(" ");
            downBridgeFormat.add("O");
        }
        String lastTile = bridge.get(currentPosition);
        if (lastTile.equals("U")) {
            if (result == BridgeGameStatus.SUCCESS) {
                upBridgeFormat.add("O");
                downBridgeFormat.add(" ");
            } else {
                upBridgeFormat.add(" ");
                downBridgeFormat.add("X");
            }
        }
        if (lastTile.equals("D")) {
            if (result == BridgeGameStatus.FAIL) {
                upBridgeFormat.add("X");
                downBridgeFormat.add(" ");
            } else {
                upBridgeFormat.add(" ");
                downBridgeFormat.add("O");
            }
        }
        printBridgeResult(upBridgeFormat, downBridgeFormat);
    }

    private static void printBridgeResult(StringJoiner upBridgeFormat, StringJoiner downBridgeFormat) {
        System.out.println(upBridgeFormat);
        System.out.println(downBridgeFormat);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }

    private enum Message {
        START("다리 건너기 게임을 시작합니다.");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
