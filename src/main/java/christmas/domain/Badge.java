package christmas.domain;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);

    private final String category;
    private final int amount;

    Badge(String category, int amount){
        this.category = category;
        this.amount = amount;
    }

    public static String findBadge(int totalBenefitAmount){
        for (Badge badge : Badge.values()){
            if (totalBenefitAmount >= badge.amount){
                return badge.category;
            }
        }
        return "없음";
    }
}