package christmas.domain;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String type;
    private final int amount;

    Badge(String type, int amount){
        this.type = type;
        this.amount = amount;
    }
}