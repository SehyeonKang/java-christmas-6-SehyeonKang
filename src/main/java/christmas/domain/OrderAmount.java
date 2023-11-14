package christmas.domain;

public class OrderAmount {

    private final int totalOrderAmount;

    public OrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public int calculateDiscountedAmount(int discountAmount) {
        return totalOrderAmount - discountAmount;
    }

    public boolean canApplyDiscount() {
        return totalOrderAmount >= 10000;
    }

    public boolean canApplyGiftBenefit() {
        return totalOrderAmount >= 120000;
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }
}