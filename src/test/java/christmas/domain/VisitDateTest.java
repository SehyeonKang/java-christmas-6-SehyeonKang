package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {

    @DisplayName("방문 날짜를 1이상 31이하의 숫자가 아닌 경우에 대한 예외 처리")
    @ValueSource(ints = {-1, 0, 32, 50})
    @ParameterizedTest
    void validateVisitDateByWrongRange(int day) {
        assertThatThrownBy(() -> new VisitDate(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문 날짜에 해당하는 할인 이벤트 찾기-크리스마스, 주말 할인")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23})
    @ParameterizedTest
    void findDiscountEventByDayWithChristmasAndWeekEnd(int day) {
        VisitDate visitDate = new VisitDate(day);
        List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();

        assertThat(discountEvents.contains(DiscountEvent.CHRISTMAS_D_DAY)).isTrue();
        assertThat(discountEvents.contains(DiscountEvent.WEEKEND)).isTrue();
    }

    @DisplayName("방문 날짜에 해당하는 할인 이벤트 찾기-크리스마스, 평일, 특별 할인")
    @ValueSource(ints = {3, 10, 17, 24, 25})
    @ParameterizedTest
    void findDiscountEventByDayWithChristmasAndWeekDayAndSpecial(int day) {
        VisitDate visitDate = new VisitDate(day);
        List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();

        assertThat(discountEvents.contains(DiscountEvent.CHRISTMAS_D_DAY)).isTrue();
        assertThat(discountEvents.contains(DiscountEvent.WEEKDAY)).isTrue();
        assertThat(discountEvents.contains(DiscountEvent.SPECIAL)).isTrue();
    }

    @DisplayName("방문 날짜에 해당하는 할인 이벤트 찾기-크리스마스, 평일 할인")
    @ValueSource(ints = {4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21})
    @ParameterizedTest
    void findDiscountEventByDayWithChristmasAndWeekDay(int day) {
        VisitDate visitDate = new VisitDate(day);
        List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();

        assertThat(discountEvents.contains(DiscountEvent.CHRISTMAS_D_DAY)).isTrue();
        assertThat(discountEvents.contains(DiscountEvent.WEEKDAY)).isTrue();
    }

    @DisplayName("방문 날짜에 해당하는 할인 이벤트 찾기-평일 할인")
    @ValueSource(ints = {26, 27, 28})
    @ParameterizedTest
    void findDiscountEventByDayWithWeekDay(int day) {
        VisitDate visitDate = new VisitDate(day);
        List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();

        assertThat(discountEvents.contains(DiscountEvent.WEEKDAY)).isTrue();
    }

    @DisplayName("방문 날짜에 해당하는 할인 이벤트 찾기-주말 할인")
    @ValueSource(ints = {29, 30})
    @ParameterizedTest
    void findDiscountEventByDayWithWeekEnd(int day) {
        VisitDate visitDate = new VisitDate(day);
        List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();

        assertThat(discountEvents.contains(DiscountEvent.WEEKEND)).isTrue();
    }

    @DisplayName("방문 날짜에 해당하는 할인 이벤트 찾기-평일, 특별 할인")
    @Test
    void findDiscountEventByDayWithWeekDay() {
        VisitDate visitDate = new VisitDate(31);
        List<DiscountEvent> discountEvents = visitDate.findDiscountEventByDay();

        assertThat(discountEvents.contains(DiscountEvent.WEEKDAY)).isTrue();
        assertThat(discountEvents.contains(DiscountEvent.SPECIAL)).isTrue();
    }

    @DisplayName("크리스마스 디데이 할인 금액 계산")
    @CsvSource(value = {"1, 1000", "2, 1100", "10, 1900", "20, 2900", "25, 3400"})
    @ParameterizedTest
    void calculateChristmasDDayDiscount(int day, int expectedDiscountAmount) {
        VisitDate visitDate = new VisitDate(day);

        assertThat(visitDate.calculateChristmasDDayDiscount()).isEqualTo(expectedDiscountAmount);
    }
}