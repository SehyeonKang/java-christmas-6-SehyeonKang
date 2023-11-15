package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

class MenuTest {

    @DisplayName("메뉴판에 있는 메뉴를 입력할 경우 해당되는 메뉴를 찾는다")
    @EnumSource(mode = EXCLUDE, names = {"EMPTY"})
    @ParameterizedTest
    void findByMenuName(Menu menu) {
        assertThat(Menu.findByMenuName(menu.getName())).isEqualTo(menu);
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력할 경우 없음을 반환한다")
    @ValueSource(strings = {"없는메뉴,콘수프,화이트와인"})
    @ParameterizedTest
    void findByMenuNameByNoMenu(String menuName) {
        assertThat(Menu.findByMenuName(menuName).getName()).isEqualTo("없음");
    }

    @DisplayName("메뉴 개수에 따라 주문 금액 계산")
    @Test
    void calculateOrderAmount() {
        assertThat(Menu.MUSHROOM_SOUP.calculateOrderAmount(5)).isEqualTo(30000);
        assertThat(Menu.CHOCOLATE_CAKE.calculateOrderAmount(10)).isEqualTo(150000);
    }
}