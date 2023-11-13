package christmas.domain;

import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;
import java.util.Map;

import static christmas.domain.Discount.*;

public class EventPlanner {
    public EventPlanner(){}

    public void run (){
        Order order = inputReservationInfo();
        if (order.isSumOverMinimumAmount()){
            Map<String, Integer> discountInfo = findDiscountInfo(order);
            Event event = new Event(discountInfo);
//            event.printDiscountInfo();
        }
        OutputView.printBenefits(order);
    }

    public Order inputReservationInfo(){
        int date = inputDate();
        Map<String, Integer> order = inputOrder();
        return new Order(date, order);
    }

    public int inputDate(){
        try {
            return InputView.readDate();
        } catch (IllegalArgumentException exception){
            OutputView.printErrorMessage(exception.getMessage());
            return inputDate();
        }
    }

    public Map<String, Integer> inputOrder() {
        try {
            return InputView.readOrder();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return inputOrder();
        }
    }

    public Map<String, Integer> findDiscountInfo(Order order){
        Map<String, Integer> discountInfo = new HashMap<>();
        if (order.isDateForChristmasEvent()){
            discountInfo.put(CHRISTMAS_D_DAY_DISCOUNT.getContent(), CHRISTMAS_D_DAY_DISCOUNT.getDiscount());
        }
        if (order.findDayOfWeek() >= 1 && order.findDayOfWeek() <= 4 || order.findDayOfWeek() == 7){
            discountInfo.put(WEEKDAY_DISCOUNT.getContent(), WEEKDAY_DISCOUNT.getDiscount());
            return discountInfo;
        }
        discountInfo.put(WEEKEND_DISCOUNT.getContent(), WEEKEND_DISCOUNT.getDiscount());
        return discountInfo;
    }
}