package christmas.domain;

public enum Discount {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 1000),
    WEEKDAY_DISCOUNT("평일 할인", 2023),
    WEEKEND_DISCOUNT("주말 할인", 2023),
    SPECIAL_DISCOUNT("특별 할인", 1000),
    GIFT("증정 이벤트", 25000);

    private final String message;
    private final int discount;

    Discount(String message, int discount){
        this.message = message;
        this.discount = discount;
    }
}