package christmas.domain;

import org.mockito.internal.matchers.Or;

import java.util.LinkedHashMap;
import java.util.Map;

public class BenefitResult {

    private final Map<DiscountEvent, Integer> benefitResult;

    public BenefitResult() {
        benefitResult = new LinkedHashMap<>();
    }

    public int applyChristMasDDayDiscount(DiscountEvent discountEvent, VisitDate visitDate) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.CHRISTMAS_D_DAY) {
            discountAmount += visitDate.calculateChristmasDDayDiscount();
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    public int applyWeekDayDiscount(DiscountEvent discountEvent, OrderMenu orderMenu) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.WEEKDAY) {
            int dessertCount = orderMenu.countDessertItems();
            discountAmount += 2023 * dessertCount;
        }
        return discountAmount;
    }

    public int applyWeekEndDiscount(DiscountEvent discountEvent, OrderMenu orderMenu) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.WEEKEND) {
            int mainCount = orderMenu.countMainItems();
            discountAmount += 2023 * mainCount;
        }
        return discountAmount;
    }
}
