package christmas.domain;

public enum ChristmasException {
    DATE_IS_EMPTY("[ERROR] 입력된 날짜가 없습니다. 날짜를 입력해주세요."),
    DATE_IS_INVALID("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DATE_IS_NOT_INTEGER("[ERROR] 숫자를 입력해주세요."),
    MENU_IS_INVALID("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");



    private final String message;

    ChristmasException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}