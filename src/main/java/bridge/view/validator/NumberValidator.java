package bridge.view.validator;

import bridge.view.exception.InputException;
import java.util.regex.Pattern;

public class NumberValidator {
    // 숫자 정규식
    private static final Pattern NNUMERIC = Pattern.compile("-?\\d+");
    // "0"으로 시작하지 않는 숫자 정규식
    private static final Pattern NNNUMERIC = Pattern.compile("-?(?:0|[1-9]\\d*)");
    // "0", "-"로 시작하지 않는 숫자 정규식
    private static final Pattern NUMERIC = Pattern.compile("[1-9]\\d*");
    private static final int MAX_INPUT_LENGTH = 10;

    public static void validate(String input) {
        validateBlank(input);
        validateNumeric(input);
        validateInteger(input);
    }

    private static void validateBlank(String target) {
        if (target == null || target.isBlank()) {
            InputException.BLANK.make();
        }
    }

    private static void validateNumeric(String target) {
        boolean onlyNumeric = NUMERIC.matcher(target).matches();
        if (!onlyNumeric) {
            InputException.NOT_NUMERIC.make();
        }
    }

    private static void validateInteger(String target) {
        try {
            Integer.parseInt(target);
        } catch (NumberFormatException e) {
            InputException.OUT_OF_RANGE.make(e);
        }
    }
}
