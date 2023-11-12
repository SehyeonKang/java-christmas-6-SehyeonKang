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
            }
        }

        return totalOrderAmount - discountAmount;
    }

    private boolean validateOrderAmount() {
        return totalOrderAmount >= 10000;
    }

}
