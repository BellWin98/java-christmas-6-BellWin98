package christmas.domain;

import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class EventPlanner {
    public EventPlanner(){}

    public void run (){
        Order order = inputReservationInfo();
        order.printOrderSheet();
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
}