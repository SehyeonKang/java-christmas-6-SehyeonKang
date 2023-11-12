package christmas.domain;

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
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    public int applyWeekEndDiscount(DiscountEvent discountEvent, OrderMenu orderMenu) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.WEEKEND) {
            int mainCount = orderMenu.countMainItems();
            discountAmount += 2023 * mainCount;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    public int applySpecialDiscount(DiscountEvent discountEvent) {
        int discountAmount = 0;
        if (discountEvent == DiscountEvent.SPECIAL) {
            discountAmount = 1000;
            benefitResult.put(discountEvent, discountAmount);
        }
        return discountAmount;
    }

    public int applyGiftDiscount(int totalOrderAmount) {
        if (totalOrderAmount >= 120000) {
            benefitResult.put(DiscountEvent.GIFT, 25000);
            return 25000;
        }
        return 0;
    }

    public int calculateTotalBenefitAmount(Menu giftMenu) {
        int totalBenefitAmount = 0;

        for (int discountAmount : benefitResult.values()) {
            totalBenefitAmount += discountAmount;
        }
        totalBenefitAmount = giftMenu.plusPrice(totalBenefitAmount);

        return totalBenefitAmount;
    }
}
