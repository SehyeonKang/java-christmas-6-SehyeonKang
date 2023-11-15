package christmas.domain;

import christmas.exception.ExceptionMessage;

import java.util.List;

public class VisitDate {

    private static final int DECEMBER_FIRST = 1;
    private static final int DECEMBER_THIRTY_FIRST = 31;
    private static final int ZERO_START_MAKER = -1;
    private static final int DAILY_INCREASE_AMOUNT = 100;
    private static final int DEFAULT_CHRISTMAS_D_DAY_DISCOUNT_AMOUNT = 1000;

    private final int day;

    public VisitDate(int day) {
        validateVisitDate(day);
        this.day = day;
    }

    private void validateVisitDate(int day) {
        if (day < DECEMBER_FIRST || day > DECEMBER_THIRTY_FIRST) {
            ExceptionMessage.INVALID_DATE.throwIllegalArgumentException();
        }
    }

    public List<DiscountEvent> findDiscountEventByDay() {
        return DiscountEvent.findByDay(day);
    }

    public int calculateChristmasDDayDiscount() {
        return (day + ZERO_START_MAKER) * DAILY_INCREASE_AMOUNT + DEFAULT_CHRISTMAS_D_DAY_DISCOUNT_AMOUNT;
    }
}