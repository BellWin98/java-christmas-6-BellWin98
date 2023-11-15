package christmas.domain;

public enum Discount {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인", 1_000),
    WEEKDAY("평일 할인", 2_023),
    WEEKEND("주말 할인", 2_023),
    SPECIAL("특별 할인", 1_000),
    GIFT("증정 이벤트", 25_000);

    private final String content;
    private final int amount;

    Discount(String content, int amount) {
        this.content = content;
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public int getAmount() {
        return amount;
    }
}