package bridge.view;

import bridge.view.exception.InputException;
import bridge.view.validator.NumberValidator;
import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    private static final InputView inputView = new InputView();

    private InputView() {
    }

    public static InputView getInputView() {
        return inputView;
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println(Message.BRIDGE_SIZE.message);
        String input = Console.readLine();
        NumberValidator.validate(input);
        System.out.println();
        return Integer.parseInt(input);
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println(Message.MOVING.message);
        String input = Console.readLine();
        validateBlank(input);
        System.out.println();
        return input;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        System.out.println(Message.GAME_COMMAND.message);
        String input = Console.readLine();
        validateBlank(input);
        System.out.println();
        return input;
    }

    private static void validateBlank(String target) {
        if (target == null || target.isBlank()) {
            InputException.BLANK.make();
        }
    }

    private enum Message {
        BRIDGE_SIZE("다리의 길이를 입력해주세요."),
        MOVING("이동할 칸을 선택해주세요. (위: U, 아래: D)"),
        GAME_COMMAND("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }
}
