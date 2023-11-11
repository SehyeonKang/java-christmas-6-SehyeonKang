package christmas.domain;

public class OrderAmount {

    private final int totalOrderAmount;

    public OrderAmount(int totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public void applyDiscount() {
        if (validateOrderAmount()) {
        }
    }

    private boolean validateOrderAmount() {
        return totalOrderAmount >= 10000;
    }
}
