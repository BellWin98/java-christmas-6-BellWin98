package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    private final Map<String, Integer> orderSheet;

    public Order(String menuInput){
        Validator.validateMenu(menuInput);
        orderSheet = toMap(menuInput);
    }

    public Map<String, Integer> toMap(String menuInput){
        Map<String, Integer> order = new HashMap<>();
        String[] menuInputSplitByComma = menuInput.split(",");
        for (String menuInfo : menuInputSplitByComma) {
            String[] menuInputSplitByHyphen = menuInfo.split("-");
            order.put(menuInputSplitByHyphen[0], Integer.parseInt(menuInputSplitByHyphen[1]));
        }
        return order;
    }

    public void printOrderSheet(){
        for (Map.Entry<String, Integer> entrySet : orderSheet.entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
        }
    }

    public int getTotalAmountBeforeDiscount(){
        return 0;
    }

    public int getTotalAmountAfterDiscount() {
        return 0;
    }

    public List<Menu> getOrderMenu(){
        return null;
    }

    public int getMenuCount(){
        return 0;
    }

//    public List<Discount> getDiscounts(){
//        return discounts;
//    }
//
//    public Badge getBadge(){
//        return badge;
//    }
}