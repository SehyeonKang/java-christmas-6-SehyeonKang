package christmas.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitResult {

    private static final int DEFAULT_DISCOUNT_AMOUNT = 0;
    private static final int DEFAULT_TOTAL_BENEFIT_AMOUNT = 0;
    private static final int WEEK_DISCOUNT_AMOUNT = 2023;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    private static final int GIFT_CHAMPAGNE_AMOUNT = 25000;

    private final Map<DiscountEvent, Integer> benefitResult;

    public BenefitResult() {
        benefitResult = new HashMap<>();
    }

    public int applyDiscountEvents(OrderAmount orderAmount, VisitDate visitDate, OrderMenu orderMenu) {
        int discountAmount = DEFAULT_DISCOUNT_AMOUNT;

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
        int discountAmount = DEFAULT_DISCOUNT_AMOUNT;
        if (discountEvent == DiscountEvent.CHRISTMAS_D_DAY) {
            discountAmount += visitDate.calculateChristmasDDayDiscount();
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    private int applyWeekDayDiscount(DiscountEvent discountEvent, OrderMenu orderMenu) {
        int discountAmount = DEFAULT_DISCOUNT_AMOUNT;
        if (discountEvent == DiscountEvent.WEEKDAY) {
            int dessertCount = orderMenu.countDessertItems();
            discountAmount += WEEK_DISCOUNT_AMOUNT * dessertCount;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    private int applyWeekEndDiscount(DiscountEvent discountEvent, OrderMenu orderMenu) {
        int discountAmount = DEFAULT_DISCOUNT_AMOUNT;
        if (discountEvent == DiscountEvent.WEEKEND) {
            int mainCount = orderMenu.countMainItems();
            discountAmount += WEEK_DISCOUNT_AMOUNT * mainCount;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    private int applySpecialDiscount(DiscountEvent discountEvent) {
        int discountAmount = DEFAULT_DISCOUNT_AMOUNT;
        if (discountEvent == DiscountEvent.SPECIAL) {
            discountAmount = SPECIAL_DISCOUNT_AMOUNT;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    public Menu applyGiftBenefit(OrderAmount orderAmount) {
        if (orderAmount.canApplyGiftBenefit()) {
            benefitResult.put(DiscountEvent.GIFT, GIFT_CHAMPAGNE_AMOUNT);
            return Menu.CHAMPAGNE;
        }

        return Menu.EMPTY;
    }

    public int calculateTotalBenefitAmount() {
        int totalBenefitAmount = DEFAULT_TOTAL_BENEFIT_AMOUNT;

        for (int discountAmount : benefitResult.values()) {
            totalBenefitAmount += discountAmount;
        }

        return totalBenefitAmount;
    }

    public Map<DiscountEvent, Integer> getBenefitResult() {
        return Collections.unmodifiableMap(benefitResult);
    }
}