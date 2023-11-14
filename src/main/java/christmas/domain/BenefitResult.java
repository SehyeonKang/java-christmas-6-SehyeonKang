package christmas.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitResult {

    private final Map<DiscountEvent, Integer> benefitResult;

    public BenefitResult() {
        benefitResult = new HashMap<>();
    }

    public int applyDiscountEvents(OrderAmount orderAmount, VisitDate visitDate, OrderMenu orderMenu) {
        int discountAmount = 0;

        if (orderAmount.canApplyDiscount()) {
            List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();
            for (DiscountEvent discountEvent : discountEvents) {
                discountAmount += applyChristMasDDayDiscount(discountEvent, visitDate);
                discountAmount += applyWeekDayDiscount(discountEvent, orderMenu);
                discountAmount += applyWeekEndDiscount(discountEvent, orderMenu);
                discountAmount += applySpecialDiscount(discountEvent);
            }
        }

        return discountAmount;
    }

    private int applyChristMasDDayDiscount(DiscountEvent discountEvent, VisitDate visitDate) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.CHRISTMAS_D_DAY) {
            discountAmount += visitDate.calculateChristmasDDayDiscount();
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    private int applyWeekDayDiscount(DiscountEvent discountEvent, OrderMenu orderMenu) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.WEEKDAY) {
            int dessertCount = orderMenu.countDessertItems();
            discountAmount += 2023 * dessertCount;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    private int applyWeekEndDiscount(DiscountEvent discountEvent, OrderMenu orderMenu) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.WEEKEND) {
            int mainCount = orderMenu.countMainItems();
            discountAmount += 2023 * mainCount;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    private int applySpecialDiscount(DiscountEvent discountEvent) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.SPECIAL) {
            discountAmount = 1000;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    public Menu applyGiftBenefit(OrderAmount orderAmount) {
        if (orderAmount.canApplyGiftBenefit()) {
            benefitResult.put(DiscountEvent.GIFT, 25000);
            return Menu.CHAMPAGNE;
        }

        return Menu.EMPTY;
    }

    public int calculateTotalBenefitAmount() {
        int totalBenefitAmount = 0;

        for (int discountAmount : benefitResult.values()) {
            totalBenefitAmount += discountAmount;
        }

        return totalBenefitAmount;
    }

    public Map<DiscountEvent, Integer> getBenefitResult() {
        return Collections.unmodifiableMap(benefitResult);
    }
}