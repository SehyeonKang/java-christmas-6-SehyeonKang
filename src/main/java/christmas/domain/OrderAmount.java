package christmas.domain;

import java.util.List;

public class OrderAmount {

    private final int totalOrderAmount;
    private final BenefitResult benefitResult;

    public OrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
        benefitResult = new BenefitResult();
    }

    public int applyDiscount(VisitDate visitDate, OrderMenu orderMenu) {
        int discountAmount = 0;

        if (validateOrderAmount()) {
            List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();
            for (DiscountEvent discountEvent : discountEvents) {
                discountAmount += benefitResult.applyChristMasDDayDiscount(discountEvent, visitDate);
                discountAmount += benefitResult.applyWeekDayDiscount(discountEvent, orderMenu);
                discountAmount += benefitResult.applyWeekEndDiscount(discountEvent, orderMenu);
                discountAmount += benefitResult.applySpecialDiscount(discountEvent);
            }
        }

        return totalOrderAmount - discountAmount;
    }

    private boolean validateOrderAmount() {
        return totalOrderAmount >= 10000;
    }

    public Menu applyGiftBenefit() {
        int discountAmount = benefitResult.applyGiftDiscount(totalOrderAmount);
        if (discountAmount > 0) {
            return Menu.CHAMPAGNE;
        }
        return Menu.EMPTY;
    }

}
