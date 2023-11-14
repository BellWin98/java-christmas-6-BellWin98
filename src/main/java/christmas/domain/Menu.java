package christmas.domain;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("애피타이저", "양송이수프", 6000),
    TAPAS("애피타이저", "타파스", 5500),
    CAESAR_SALAD("애피타이저", "시저샐러드", 8000),
    T_BONE_STEAK("메인", "티본스테이크", 55000),
    BBQ_RIBS("메인", "바비큐립", 54000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25000),
    CHOCOLATE_CAKE("디저트", "초코케이크", 15000),
    ICE_CREAM("디저트", "아이스크림", 5000),
    ZERO_COKE("음료", "제로콜라", 3000),
    RED_WINE("음료", "레드와인", 60000),
    CHAMPAGNE("음료", "샴페인", 25000);

    private final String category;
    private final String name;
    private final int price;

    Menu(String category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static boolean hasMenu(String menuName) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.name.equals(menuName));
    }

    public static boolean isMenuCategoryDrinks(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .map(menu -> menu.category.equals("음료"))
                .orElse(false);
    }

    public static boolean isMenuCategoryMain(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .map(menu -> menu.category.equals("메인"))
                .orElse(false);
    }

    public static boolean isMenuCategoryDessert(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .map(menu -> menu.category.equals("디저트"))
                .orElse(false);
    }

    public static int calculateSumOfMenuPrice(String menuName, int count) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .map(menu -> menu.price * count)
                .orElse(0);
    }
}