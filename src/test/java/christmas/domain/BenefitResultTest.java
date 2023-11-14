package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BenefitResultTest {

    OrderAmount orderAmount = new OrderAmount(10000);
    BenefitResult benefitResult;

    @BeforeEach
    void beforeEach() {
        benefitResult = new BenefitResult();
    }

    @DisplayName("총 주문 금액이 10000원 미만일 경우 이벤트 적용 안 됨")
    @ValueSource(ints = {0, 1000, 9999})
    @ParameterizedTest
    void applyDiscountEventsByAmountLessThan10000(int amount) {
        OrderAmount amountBySixThousand = new OrderAmount(amount);
        VisitDate visitDate = new VisitDate(1);
        OrderMenu orderMenu = new OrderMenu(Map.of("양송이수프", 1));

        int discountAmount = benefitResult.applyDiscountEvents(amountBySixThousand, visitDate, orderMenu);

        assertThat(discountAmount).isEqualTo(0);
        assertThat(benefitResult.getBenefitResult().size()).isEqualTo(0);
    }

    @DisplayName("크리스마스 디데이 할인 이벤트 적용")
    @Test
    void applyChristmasDDayDiscount() {
        VisitDate visitDate = new VisitDate(15);
        OrderMenu orderMenu = new OrderMenu(Map.of("초코케이크", 1));

        int discountAmount = benefitResult.applyDiscountEvents(orderAmount, visitDate, orderMenu);

        assertThat(discountAmount).isEqualTo(2400);
        assertThat(benefitResult.getBenefitResult().containsKey(DiscountEvent.CHRISTMAS_D_DAY)).isTrue();
    }

    @DisplayName("평일 할인 이벤트 적용")
    @Test
    void applyWeekDayDiscount() {
        VisitDate visitDate = new VisitDate(27);
        OrderMenu orderMenu = new OrderMenu(Map.of("초코케이크", 1));

        int discountAmount = benefitResult.applyDiscountEvents(orderAmount, visitDate, orderMenu);

        assertThat(discountAmount).isEqualTo(2023);
        assertThat(benefitResult.getBenefitResult().containsKey(DiscountEvent.WEEKDAY)).isTrue();
    }

    @DisplayName("주말 할인 이벤트 적용")
    @Test
    void applyWeekEndDiscount() {
        VisitDate visitDate = new VisitDate(29);
        OrderMenu orderMenu = new OrderMenu(Map.of("티본스테이크", 1));

        int discountAmount = benefitResult.applyDiscountEvents(orderAmount, visitDate, orderMenu);

        assertThat(discountAmount).isEqualTo(2023);
        assertThat(benefitResult.getBenefitResult().containsKey(DiscountEvent.WEEKEND)).isTrue();
    }

    @DisplayName("특별 할인 이벤트 적용")
    @Test
    void applySpecialDiscount() {
        VisitDate visitDate = new VisitDate(31);
        OrderMenu orderMenu = new OrderMenu(Map.of("티본스테이크", 1));

        int discountAmount = benefitResult.applyDiscountEvents(orderAmount, visitDate, orderMenu);

        assertThat(discountAmount).isEqualTo(1000);
        assertThat(benefitResult.getBenefitResult().containsKey(DiscountEvent.SPECIAL)).isTrue();
    }

    @DisplayName("증정 이벤트 적용")
    @Test
    void applyGiftBenefit() {
        OrderAmount orderAmountCanGetGift = new OrderAmount(120000);

        Menu giftMenu = benefitResult.applyGiftBenefit(orderAmountCanGetGift);

        assertThat(giftMenu).isEqualTo(Menu.CHAMPAGNE);
        assertThat(benefitResult.getBenefitResult().containsKey(DiscountEvent.GIFT)).isTrue();
    }

    @DisplayName("증정 이벤트 미적용")
    @Test
    void applyNoGiftBenefit() {
        Menu giftMenu = benefitResult.applyGiftBenefit(orderAmount);

        assertThat(giftMenu).isEqualTo(Menu.EMPTY);
        assertThat(benefitResult.getBenefitResult().containsKey(DiscountEvent.GIFT)).isFalse();
        assertThat(benefitResult.getBenefitResult().size()).isEqualTo(0);
    }

    @DisplayName("총혜택 금액 계산")
    @Test
    void calculateTotalBenefitAmount() {
        VisitDate visitDate = new VisitDate(25);
        OrderMenu orderMenu = new OrderMenu(Map.of("초코케이크", 1, "양송이수프", 2));

        benefitResult.applyDiscountEvents(orderAmount, visitDate, orderMenu);
        int totalBenefitAmount = benefitResult.calculateTotalBenefitAmount();

        assertThat(totalBenefitAmount).isEqualTo(6423);
    }
}