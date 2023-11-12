package christmas.domain;

import java.util.regex.Pattern;

import static christmas.domain.ChristmasException.*;

public class Validator {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;
    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 20;
    private static final Pattern INT_FORMAT = Pattern.compile("\\d+");

    public static void validateDate(String value){
        if (value.isEmpty()) {
            throw new IllegalArgumentException(DATE_IS_EMPTY.getMessage());
        }
        if (!INT_FORMAT.matcher(value).matches()){
            throw new IllegalArgumentException(DATE_IS_INVALID.getMessage());
        }
        int date = Integer.parseInt(value);
        if (date < START_DATE || date > END_DATE) {
            throw new IllegalArgumentException(DATE_IS_INVALID.getMessage());
        }
    }

    public static void validateMenu(String value){
        if (value.isEmpty()){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
        // 메뉴판에 있는 메뉴인지 확인
        if (!Menu.hasMenu(value)) {
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
    }

    public static void validateCountOfMenu(String value){
        if (value.isEmpty()){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
        if (!INT_FORMAT.matcher(value).matches()){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
        int count = Integer.parseInt(value);
        if (count < MIN_COUNT || count > MAX_COUNT){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
    }
}
