package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.INCLUDE;

class MenuGroupTest {

    @DisplayName("애피타이저 메뉴를 입력했을 때 애피타이저 메뉴 그룹을 찾는다")
    @EnumSource(mode = INCLUDE, names = {"MUSHROOM_SOUP", "TAPAS", "CAESAR_SALAD"})
    @ParameterizedTest
    void findByMenuByAppetizer(Menu menu) {
        assertThat(MenuGroup.findByMenu(menu)).isEqualTo(MenuGroup.APPETIZER);
    }

    @DisplayName("메인 메뉴를 입력했을 때 메인 메뉴 그룹을 찾는다")
    @EnumSource(mode = INCLUDE, names = {"T_BONE_STEAK", "BBQ_RIBS", "SEAFOOD_PASTA", "CHRISTMAS_PASTA"})
    @ParameterizedTest
    void findByMenuByMain(Menu menu) {
        assertThat(MenuGroup.findByMenu(menu)).isEqualTo(MenuGroup.MAIN);
    }

    @DisplayName("디저트 메뉴를 입력했을 때 디저트 메뉴 그룹을 찾는다")
    @EnumSource(mode = INCLUDE, names = {"CHOCOLATE_CAKE", "ICE_CREAM"})
    @ParameterizedTest
    void findByMenuByDessert(Menu menu) {
        assertThat(MenuGroup.findByMenu(menu)).isEqualTo(MenuGroup.DESSERT);
    }

    @DisplayName("음료 메뉴를 입력했을 때 음료 메뉴 그룹을 찾는다")
    @EnumSource(mode = INCLUDE, names = {"ZERO_COLA", "RED_WINE", "CHAMPAGNE"})
    @ParameterizedTest
    void findByMenuByBeverage(Menu menu) {
        assertThat(MenuGroup.findByMenu(menu)).isEqualTo(MenuGroup.BEVERAGE);
    }
}