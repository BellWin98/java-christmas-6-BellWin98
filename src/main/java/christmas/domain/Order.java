package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class Order {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS_EVENT_START_DATE = 1;
    private static final int CHRISTMAS_EVENT_END_DATE = 25;
    private static final int MINIMUM_ORDER_AMOUNT = 10000;
    private final int reservationDate;
    private final Map<String, Integer> order;

    public Order(int reservationDate, Map<String, Integer> order){
        this.reservationDate = reservationDate;
        this.order = order;
    }

    public int findDayOfWeek(){
        LocalDate date = LocalDate.of(YEAR, MONTH, reservationDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public boolean isDateForChristmasEvent(){
        return reservationDate >= CHRISTMAS_EVENT_START_DATE && reservationDate <= CHRISTMAS_EVENT_END_DATE;
    }

    public boolean isSumOverMinimumAmount(){
        int sum = 0;
        for (Map.Entry<String, Integer> orderSheet : order.entrySet()){
            sum += Menu.calculateSumOfPrice(orderSheet.getKey(), orderSheet.getValue());
        }
        return (sum > MINIMUM_ORDER_AMOUNT);
    }

    public int getTotalAmountBeforeDiscount(){
        int sum = 0;
        for (Map.Entry<String, Integer> orderSheet : order.entrySet()){
            sum += Menu.calculateSumOfPrice(orderSheet.getKey(), orderSheet.getValue());
        }
        return sum;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public int getReservationDate() {
        return reservationDate;
    }
}