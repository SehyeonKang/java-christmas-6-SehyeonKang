package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderAmountTest {

    @DisplayName("할인 후 예상 결제 금액을 계산한다")
    @Test
    void calculateDiscountedAmount() {
        OrderAmount orderAmount = new OrderAmount(10000);

        int discountedAmount = orderAmount.calculateDiscountedAmount(2000);

        assertThat(discountedAmount).isEqualTo(8000);
    }

    @DisplayName("이벤트 적용 확인을 위해 총주문 금액이 10000원이상인지 확인한다.")
    @Test
    void canApplyDiscount() {
        OrderAmount orderAmount = new OrderAmount(10000);
        OrderAmount orderAmountWithLowNumber = new OrderAmount(1000);

        assertThat(orderAmount.canApplyDiscount()).isTrue();
        assertThat(orderAmountWithLowNumber.canApplyDiscount()).isFalse();
    }

    @DisplayName("증정 이벤트 적용 확인을 위해 할인 전 총주문 금액이 120000원이상인지 확인한다.")
    @Test
    void canApplyGiftBenefit() {
        OrderAmount orderAmount = new OrderAmount(120000);
        OrderAmount orderAmountWithLowNumber = new OrderAmount(1000);

        assertThat(orderAmount.canApplyGiftBenefit()).isTrue();
        assertThat(orderAmountWithLowNumber.canApplyGiftBenefit()).isFalse();
    }
}