package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static christmas.domain.Discount.*;

public class EventCalendar {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS_EVENT_START_DATE = 1;
    private static final int CHRISTMAS_EVENT_END_DATE = 25;

    public EventCalendar() {
    }

    public List<String> findEvent(int date) {
        List<String> events = new ArrayList<>();
        if (isDateForChristmasEvent(date)) {
            events.add(CHRISTMAS_D_DAY.getContent());
        }
        if (findDayOfWeek(date) == 7 || isDateChristmas(date)) {
            events.add(SPECIAL.getContent());
        }
        if (findDayOfWeek(date) >= 1 && findDayOfWeek(date) <= 4 || findDayOfWeek(date) == 7) {
            events.add(WEEKDAY.getContent());
            return events;
        }
        events.add(WEEKEND.getContent());
        return events;
    }

    public int findDayOfWeek(int reservationDate) {
        LocalDate date = LocalDate.of(YEAR, MONTH, reservationDate);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public boolean isDateForChristmasEvent(int date) {
        return date >= CHRISTMAS_EVENT_START_DATE && date <= CHRISTMAS_EVENT_END_DATE;
    }

    public boolean isDateChristmas(int date) {
        return date == CHRISTMAS_EVENT_END_DATE;
    }
}
