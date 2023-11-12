package christmas.domain;

import java.util.Map;

public class Order {
    private final int date;
    private final Map<String, Integer> order;

    public Order(int date, Map<String, Integer> order){
        this.date = date;
        this.order = order;
    }

    public void printOrderSheet(){
        for (Map.Entry<String, Integer> entrySet : order.entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
        }
    }
}