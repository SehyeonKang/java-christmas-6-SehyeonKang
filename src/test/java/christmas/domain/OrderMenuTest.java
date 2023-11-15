package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenuTest {

    @DisplayName("주문 메뉴 입력에 따른 주문 메뉴 객체 생성")
    @Test
    void createOrderMenu() {
        OrderMenu orderMenu = new OrderMenu(Map.of("시저샐러드", 1, "바비큐립", 1, "제로콜라", 2));
        Map<Menu, Integer> orderItems = orderMenu.getOrderItems();

        assertThat(orderItems.containsKey(Menu.CAESAR_SALAD)).isTrue();
        assertThat(orderItems.containsKey(Menu.BBQ_RIBS)).isTrue();
        assertThat(orderItems.containsKey(Menu.ZERO_COLA)).isTrue();
        assertThat(orderItems.get(Menu.CAESAR_SALAD)).isEqualTo(1);
        assertThat(orderItems.get(Menu.BBQ_RIBS)).isEqualTo(1);
        assertThat(orderItems.get(Menu.ZERO_COLA)).isEqualTo(2);
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력했을 경우에 대한 예외 처리")
    @CsvSource(value = {"없는메뉴, 1", "콘수프, 2", "화이트와인, 1"})
    @ParameterizedTest
    void createOrderMenuByWrongMenu(String menuName, int menuCount) {
        assertThatThrownBy(() -> new OrderMenu(Map.of(menuName, menuCount)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("음료만 입력했을 경우에 대한 예외 처리")
    @Test
    void createOrderMenuOnlyBeverage() {
        assertThatThrownBy(() -> new OrderMenu(Map.of("제로콜라", 1, "레드와인", 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴를 1개 미만 또는 21개 이상 주문했을 경우에 대한 예외 처리")
    @Test
    void createOrderMenuByWrongRange() {
        assertThatThrownBy(() -> new OrderMenu(Map.of("양송이수프", 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

        assertThatThrownBy(() -> new OrderMenu(Map.of("양송이수프", 21)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
    }

    @DisplayName("총주문 금액을 계산한다")
    @Test
    void calculateTotalOrderAmount() {
        OrderMenu orderMenu = new OrderMenu(Map.of("양송이수프", 2, "초코케이크", 1));
        int totalOrderAmount = orderMenu.calculateTotalOrderAmount();

        assertThat(totalOrderAmount).isEqualTo(27000);
    }

    @DisplayName("주문 메뉴중 디저트 메뉴의 개수를 구한다")
    @Test
    void countDessertItems() {
        OrderMenu orderMenu = new OrderMenu(Map.of("양송이수프", 2, "초코케이크", 1, "아이스크림", 3));
        int dessertCount = orderMenu.countDessertItems();

        assertThat(dessertCount).isEqualTo(4);
    }

    @DisplayName("주문 메뉴중 메인 메뉴의 개수를 구한다")
    @Test
    void countMainItems() {
        OrderMenu orderMenu = new OrderMenu(Map.of("양송이수프", 1, "바비큐립", 2, "해산물파스타", 5));
        int mainCount = orderMenu.countMainItems();

        assertThat(mainCount).isEqualTo(7);
    }
}