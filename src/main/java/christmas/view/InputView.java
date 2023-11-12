package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.ChristmasException;
import christmas.domain.Menu;
import christmas.domain.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.ChristmasException.MENU_IS_INVALID;
import static christmas.domain.ChristmasException.MENU_IS_ONLY_DRINKS;

public class InputView {
    public static int readDate(){
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String dateInput = Console.readLine();
        Validator.validateDate(dateInput);

        return Integer.parseInt(dateInput);
    }

    public static Map<String, Integer> readOrder(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orderInput = Console.readLine();
        Map<String, Integer> order = separateOrderIntoMenuAndCount(orderInput);
        validateOrderOnlyContainsDrinks(order);
        return order;
    }

    public static Map<String, Integer> separateOrderIntoMenuAndCount(String value){
        Map<String, Integer> orderSheet = new HashMap<>();
        String[] orders = value.split(",");
        for (String order : orders){
            String[] menuAndCount = order.split("-");
            Validator.validateMenu(menuAndCount[0]);
            Validator.validateCountOfMenu(menuAndCount[1]);
            if (orderSheet.containsKey(menuAndCount[0])){
                throw new IllegalArgumentException(MENU_IS_INVALID.getMessage());
            }
            orderSheet.put(menuAndCount[0], Integer.parseInt(menuAndCount[1]));
        }
        return orderSheet;
    }

    public static void validateOrderOnlyContainsDrinks(Map<String, Integer> order){
        int count = 0;
        for (String key : order.keySet()){
            if (Menu.isMenuTypeDrinks(key)){
                count++;
            }
        }
        if (count == order.size()){
            throw new IllegalArgumentException(MENU_IS_ONLY_DRINKS.getMessage());
        }
    }
}