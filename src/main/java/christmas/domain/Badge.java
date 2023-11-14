package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000);

    private final String category;
    private final int amount;

    Badge(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public static String findBadge(int totalBenefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> totalBenefitAmount >= badge.amount)
                .findFirst()
                .map(Badge::getCategory)
                .orElse("없음");
    }

    public String getCategory() {
        return category;
    }
}