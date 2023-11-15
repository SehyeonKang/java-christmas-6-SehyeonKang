package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountEventTest {

    @DisplayName("크리스마스 디데이, 주말 할인에만 해당하는 날짜를 입력할 경우")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23})
    @ParameterizedTest
    void findByDayWithChristmasAndWeekEndDiscount(int day) {
        List<DiscountEvent> events = DiscountEvent.findByDay(day);

        assertThat(events.contains(DiscountEvent.CHRISTMAS_D_DAY)).isTrue();
        assertThat(events.contains(DiscountEvent.WEEKEND)).isTrue();
    }

    @DisplayName("크리스마스 디데이, 평일 할인에만 해당하는 날짜를 입력할 경우")
    @ValueSource(ints = {4, 5, 6, 7, 11, 12, 13, 14, 18, 19, 20, 21})
    @ParameterizedTest
    void findByDayWithChristmasAndWeekDayDiscount(int day) {
        List<DiscountEvent> events = DiscountEvent.findByDay(day);

        assertThat(events.contains(DiscountEvent.CHRISTMAS_D_DAY)).isTrue();
        assertThat(events.contains(DiscountEvent.WEEKDAY)).isTrue();
    }

    @DisplayName("크리스마스 디데이, 평일 할인, 특별 할인에만 해당하는 날짜를 입력할 경우")
    @ValueSource(ints = {3, 10, 17, 24, 25})
    @ParameterizedTest
    void findByDayWithChristmasAndWeekEndAndSpecialDiscount(int day) {
        List<DiscountEvent> events = DiscountEvent.findByDay(day);

        assertThat(events.contains(DiscountEvent.CHRISTMAS_D_DAY)).isTrue();
        assertThat(events.contains(DiscountEvent.WEEKDAY)).isTrue();
        assertThat(events.contains(DiscountEvent.SPECIAL)).isTrue();
    }

    @DisplayName("평일 할인에만 해당하는 날짜를 입력할 경우")
    @ValueSource(ints = {26, 27, 28})
    @ParameterizedTest
    void findByDayWithWeekDayDiscount(int day) {
        List<DiscountEvent> events = DiscountEvent.findByDay(day);

        assertThat(events.contains(DiscountEvent.WEEKDAY)).isTrue();
    }

    @DisplayName("주말 할인에만 해당하는 날짜를 입력할 경우")
    @ValueSource(ints = {29, 30})
    @ParameterizedTest
    void findByDayWithWeekEndDiscount(int day) {
        List<DiscountEvent> events = DiscountEvent.findByDay(day);

        assertThat(events.contains(DiscountEvent.WEEKEND)).isTrue();
    }

    @DisplayName("평일 할인, 특별 할인에만 해당하는 날짜를 입력할 경우")
    @Test
    void findByDayWithWeekEndAndSpecialDiscount() {
        List<DiscountEvent> events = DiscountEvent.findByDay(31);

        assertThat(events.contains(DiscountEvent.WEEKDAY)).isTrue();
        assertThat(events.contains(DiscountEvent.SPECIAL)).isTrue();
    }
}