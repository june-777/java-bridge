package bridge.view.exception;

public enum InputException {
    BLANK("공백 입력은 불가 합니다."),
    NOT_NUMERIC("숫자만 입력 가능합니다."),
    OUT_OF_RANGE("정수형 범위를 넘어섰습니다.");

    private final String message;

    InputException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void make() {
        throw new IllegalArgumentException(getMessage());
    }

    public void make(Throwable throwable) {
        throw new IllegalArgumentException(getMessage(), throwable);
    }
}