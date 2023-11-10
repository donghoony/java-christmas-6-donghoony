package christmas.exception;

public enum ExceptionMessage {
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
