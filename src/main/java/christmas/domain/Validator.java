package christmas.domain;

import christmas.view.ErrorView;
import christmas.view.InputView;

import java.util.regex.PatternSyntaxException;

import static christmas.domain.ChristmasException.*;

public class Validator {
    private static final int START_DATE = 1;
    private static final int END_DATE = 31;
    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 20;

    public static void validateDate(String userInput){
        try{
            // 공백 여부 확인
            if (userInput.isEmpty()){
                throw new IllegalArgumentException(DATE_IS_EMPTY.getMessage());
            }
            // 숫자 여부 확인
            int date = Integer.parseInt(userInput);
            // 날짜 유효 여부 확인
            if (date < START_DATE || date > END_DATE){
                throw new IllegalArgumentException(DATE_IS_INVALID.getMessage());
            }
        } catch (IllegalArgumentException e){
            ErrorView.printErrorMessage(e.getMessage());
            new EventPlanner();
        }
    }

    public static void validateMenu(String userInput){
        try {
            String[] userInputSplitByComma = userInput.split(",");
            for (String s : userInputSplitByComma){
                String[] userInputSplitByHyphen = s.split("-");
                validateString(userInputSplitByHyphen[0]);
                validateNumber(userInputSplitByHyphen[1]);
            }
        } catch (PatternSyntaxException e){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        } catch (IllegalArgumentException e){
            ErrorView.printErrorMessage(e.getMessage());
            new Order(InputView.readMenu());
        }
    }

    private static void validateString(String userInput){
        // 공백 여부 확인
        if (userInput.isEmpty()){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
        // 메뉴판에 있는 메뉴인지 확인
        if (!Menu.hasMenu(userInput)){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
    }

    private static void validateNumber(String userInput){
        // 공백 여부 확인
        if (userInput.isEmpty()){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
        // 숫자 여부 확인
        int count = Integer.parseInt(userInput);
        // 메뉴 개수가 1 ~ 20 사이인지 확인
        if (count < MIN_COUNT || count > MAX_COUNT){
            throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
        }
    }
}
