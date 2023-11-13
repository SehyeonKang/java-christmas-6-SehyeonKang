package christmas.exception;

public enum ExceptionMessage {
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATE_ORDER("중복된 메뉴를 주문했습니다. 다시 입력해 주세요."),
    ONLY_BEVERAGE("음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    LIMIT_EXCEEDED_ORDER("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private static final String TAG_ERROR = "[ERROR] ";
    private final String message;

    ExceptionMessage(String message) {
        this.message = TAG_ERROR + message;
    }

    public void throwIllegalArgumentException() {
        throw new IllegalArgumentException(message);
    }
}
