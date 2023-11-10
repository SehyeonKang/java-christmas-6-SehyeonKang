package christmas.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum MenuGroup {
    APPETIZER("애피타이저", Arrays.asList(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(Menu.T_BONE_STEAK, Menu.BBQ_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    BEVERAGE("음료", Arrays.asList(Menu.ZERO_COLA, Menu.RED_WINE, Menu.CHAMPAGNE)),
    EMPTY("없음", Collections.emptyList());

    private final String groupName;
    private final List<Menu> menuItems;

    MenuGroup(String groupName, List<Menu> menuItems) {
        this.groupName = groupName;
        this.menuItems = menuItems;
    }

    public static MenuGroup findByMenu(Menu menu) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasMenu(menu))
                .findAny()
                .orElse(EMPTY);
    }

    public boolean hasMenu(Menu menu) {
        return menuItems.stream()
                .anyMatch(menuItem -> menuItem == menu);
    }
}
