package christmas.domain;

import christmas.view.InputView;

public class EventPlanner {

    private final int reservationDate;
    public EventPlanner(){
        String dateInput = InputView.readDate();
        Validator.validateDate(dateInput);
        reservationDate = Integer.parseInt(dateInput);
    }

    public void run (){
        Order order = new Order(InputView.readMenu());
        order.printOrderSheet();
    }


}
