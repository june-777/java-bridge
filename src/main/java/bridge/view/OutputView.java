package bridge.view;

import bridge.domain.MoveResult;
import bridge.domain.MovingStatus;
import java.util.List;
import java.util.StringJoiner;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    private static final String CORRECT = "O";
    private static final String WRONG = "X";
    private static final String EMPTY = " ";
    private static final String UP = "U";
    private static final String DOWN = "D";
    private static final OutputView outputView = new OutputView();
    private StringJoiner upBridgeFormat;
    private StringJoiner downBridgeFormat;

    private OutputView() {
    }

    public static OutputView getOutputView() {
        return outputView;
    }

    public void printStart() {
        System.out.println(Message.START.message);
        System.out.println();
    }

    public void printMap(List<String> bridge, int currentPosition, MovingStatus result) {
        initBridgeFormat();
        updateBridgeFormat(bridge, currentPosition, result);
        printBridgeResult(upBridgeFormat, downBridgeFormat);
    }

    public void printResult(List<String> bridge, MoveResult result, int tryCount) {
        System.out.println(Message.RESULT.message);
        initBridgeFormat();
        updateBridgeFormat(bridge, result.currentPosition(), result.movingStatus());
        printBridgeResult(upBridgeFormat, downBridgeFormat);
        System.out.printf(Message.SUCCESS_OR_NOT.message, result.movingStatus().getKoreanText());
        System.out.println();
        System.out.printf(Message.TOTAL_TRY_COUNT.message, tryCount);
    }

    private void initBridgeFormat() {
        upBridgeFormat = new StringJoiner(" | ", "[ ", " ]");
        downBridgeFormat = new StringJoiner(" | ", "[ ", " ]");
    }

    private void updateBridgeFormat(List<String> bridge, int currentPosition, MovingStatus result) {
        addBeforeCurrentPosition(bridge, currentPosition);
        String lastTile = bridge.get(currentPosition);
        addCurrentPosition(result, lastTile, upBridgeFormat, downBridgeFormat);
    }

    private void addBeforeCurrentPosition(List<String> bridge, int currentPosition) {
        for (int position = 0; position < currentPosition; position++) {
            String tile = bridge.get(position);
            addCorrectTileSign(tile, upBridgeFormat, downBridgeFormat);
        }
    }

    private void addCurrentPosition(MovingStatus result, String lastTile, StringJoiner upBridgeFormat,
                                    StringJoiner downBridgeFormat) {
        if (lastTile.equals(UP)) {
            updateAboutLastTileIsU(result, upBridgeFormat, downBridgeFormat);
        }
        if (lastTile.equals(DOWN)) {
            updateAboutLastTileIsD(result, upBridgeFormat, downBridgeFormat);
        }
    }

    private static void updateAboutLastTileIsU(MovingStatus result, StringJoiner upBridgeFormat,
                                               StringJoiner downBridgeFormat) {
        if (result == MovingStatus.SUCCESS) {
            addCorrectSign(upBridgeFormat);
            addEmptySign(downBridgeFormat);
            return;
        }
        addEmptySign(upBridgeFormat);
        addWrongSign(downBridgeFormat);
    }

    private void updateAboutLastTileIsD(MovingStatus result, StringJoiner upBridgeFormat,
                                        StringJoiner downBridgeFormat) {
        if (result == MovingStatus.FAIL) {
            addWrongSign(upBridgeFormat);
            addEmptySign(downBridgeFormat);
            return;
        }
        addEmptySign(upBridgeFormat);
        addCorrectSign(downBridgeFormat);
    }

    private static void addCorrectTileSign(String tile, StringJoiner upBridgeFormat, StringJoiner downBridgeFormat) {
        if (tile.equals(UP)) {
            addCorrectSign(upBridgeFormat);
            addEmptySign(downBridgeFormat);
            return;
        }
        addEmptySign(upBridgeFormat);
        addCorrectSign(downBridgeFormat);
    }

    private static void addWrongSign(StringJoiner upBridgeFormat) {
        upBridgeFormat.add(WRONG);
    }

    private static void addEmptySign(StringJoiner downBridgeFormat) {
        downBridgeFormat.add(EMPTY);
    }

    private static StringJoiner addCorrectSign(StringJoiner upBridgeFormat) {
        return upBridgeFormat.add(CORRECT);
    }

    private static void printBridgeResult(StringJoiner upBridgeFormat, StringJoiner downBridgeFormat) {
        System.out.println(upBridgeFormat);
        System.out.println(downBridgeFormat);
        System.out.println();
    }

    private enum Message {
        START("다리 건너기 게임을 시작합니다."),
        RESULT("최종 게임 결과"),
        SUCCESS_OR_NOT("게임 성공 여부: %s"),
        TOTAL_TRY_COUNT("총 시도한 횟수: %d");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
