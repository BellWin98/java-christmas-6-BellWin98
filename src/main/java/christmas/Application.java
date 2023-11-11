package christmas;

import christmas.domain.EventPlanner;
import christmas.view.ErrorView;

public class Application {
    public static void main(String[] args) {
            EventPlanner eventPlanner = new EventPlanner();
            eventPlanner.run();
    }
}
