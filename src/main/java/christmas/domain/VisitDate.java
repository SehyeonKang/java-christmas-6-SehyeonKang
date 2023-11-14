package christmas.domain;

import christmas.exception.ExceptionMessage;

import java.util.List;

public class VisitDate {

    private final int day;

    public VisitDate(int day) {
        validateVisitDate(day);
        this.day = day;
    }

    private void validateVisitDate(int day) {
        if (day < 1 || day > 31) {
            ExceptionMessage.INVALID_DATE.throwIllegalArgumentException();
        }
    }

    public List<DiscountEvent> findDiscountEventByDay() {
        return DiscountEvent.findByDay(day);
    }

    public int calculateChristmasDDayDiscount() {
        return (day - 1) * 100 + 1000;
    }
}