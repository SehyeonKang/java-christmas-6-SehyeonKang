package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class EventBadgeTest {

    @DisplayName("총혜택 금액이 20000원 이상일 경우 산타 배지 부여")
    @ValueSource(ints = {20000, 30000, 100000})
    @ParameterizedTest
    void findEventBadgeBySanta(int totalBenefitAmount) {
        EventBadge eventBadge = EventBadge.findEventBadge(totalBenefitAmount);

        assertThat(eventBadge.getName()).isEqualTo("산타");
    }

    @DisplayName("총혜택 금액이 10000원 이상, 20000원 미만일 경우 트리 배지 부여")
    @ValueSource(ints = {10000, 15000, 19999})
    @ParameterizedTest
    void findEventBadgeByTree(int totalBenefitAmount) {
        EventBadge eventBadge = EventBadge.findEventBadge(totalBenefitAmount);

        assertThat(eventBadge.getName()).isEqualTo("트리");
    }

    @DisplayName("총혜택 금액이 5000원 이상, 10000원 미만일 경우 별 배지 부여")
    @ValueSource(ints = {5000, 7000, 9999})
    @ParameterizedTest
    void findEventBadgeByStar(int totalBenefitAmount) {
        EventBadge eventBadge = EventBadge.findEventBadge(totalBenefitAmount);

        assertThat(eventBadge.getName()).isEqualTo("별");
    }

    @DisplayName("총혜택 금액이 5000원 미만일 경우 이벤트 배지 부여 안 함")
    @ValueSource(ints = {0, 1000, 4999})
    @ParameterizedTest
    void findEventBadgeByEmpty(int totalBenefitAmount) {
        EventBadge eventBadge = EventBadge.findEventBadge(totalBenefitAmount);

        assertThat(eventBadge.getName()).isEqualTo("없음");
    }
}