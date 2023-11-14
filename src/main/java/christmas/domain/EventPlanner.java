package christmas.domain;

import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;
import java.util.Map;

public class EventPlanner {
    private final EventCalendar eventCalendar = new EventCalendar();

    public EventPlanner() {
    }

    public void run() {
        Order order = inputReservationInfo();
        if (order.isSumOverMinimumOrderAmount()) {
            List<String> events = eventCalendar.findEvent(order.getReservationDate());
            order.applyBenefits(events);
        }
        OutputView.printBenefits(order);
    }

    private Order inputReservationInfo() {
        int date = inputDate();
        Map<String, Integer> order = inputOrder();
        return new Order(date, order);
    }

    private int inputDate() {
        try {
            return InputView.readDate();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return inputDate();
        }
    }

    private Map<String, Integer> inputOrder() {
        try {
            return InputView.readOrder();
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return inputOrder();
        }
    }
}