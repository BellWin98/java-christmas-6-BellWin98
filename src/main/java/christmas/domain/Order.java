package christmas.domain;

import java.util.List;
import java.util.Map;

public class Order {

    private final Map<Menu, Integer> orderSheet;
    private final List<Discount> discounts;
    private final Badge badge;

    public Order(String order){

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

    public List<Discount> getDiscounts(){
        return discounts;
    }

    public Badge getBadge(){
        return badge;
    }
}