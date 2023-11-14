package christmas.exception;

public enum ChristmasException {
    DATE_IS_EMPTY_ERROR("[ERROR] 입력된 날짜가 없습니다. 날짜를 입력해주세요."),
    DATE_IS_INVALID_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    MENU_IS_INVALID_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_IS_ONLY_DRINKS_ERROR("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    MENU_COUNT_OVER_TWENTY_ERROR("[ERROR] 메뉴는 한 번에 최대 20개까지 주문할 수 있습니다. 다시 입력해주세요.");


    private final String message;

    ChristmasException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}