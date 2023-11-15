package christmas.domain;

public class OrderAmount {

    private static final int MINIMUM_DISCOUNT_POSSIBLE_AMOUNT = 10000;
    private static final int MINIMUM_GIFT_BENEFIT_POSSIBLE_AMOUNT = 120000;

    private final int totalOrderAmount;

    public OrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public int calculateDiscountedAmount(int discountAmount) {
        return totalOrderAmount - discountAmount;
    }

    public boolean canApplyDiscount() {
        return totalOrderAmount >= MINIMUM_DISCOUNT_POSSIBLE_AMOUNT;
    }

    public boolean canApplyGiftBenefit() {
        return totalOrderAmount >= MINIMUM_GIFT_BENEFIT_POSSIBLE_AMOUNT;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }
}