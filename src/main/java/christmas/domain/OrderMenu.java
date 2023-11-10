package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class OrderMenu {

    private final Map<Menu, Integer> orderItems;

    public OrderMenu(Map<String, Integer> orderedItems) {
        orderItems = new HashMap<>();
        saveOrderedItems(orderedItems);
        validateOnlyBeverage();
        validateTotalItemCount();
    }

    private void saveOrderedItems(Map<String, Integer> orderedItems) {
        for (String menuName : orderedItems.keySet()) {
            Menu menu = Menu.findByMenuName(menuName);
            orderItems.put(menu, orderedItems.get(menuName));
        }
    }

    private void validateOnlyBeverage() {
        int beverageCount = 0;
        for (Menu menu : orderItems.keySet()) {
            if (MenuGroup.findByMenu(menu) == MenuGroup.BEVERAGE) {
                beverageCount++;
            }
        }

        if (beverageCount == orderItems.size()) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private void validateTotalItemCount() {
        int totalItemCount = 0;
        for (Menu menu : orderItems.keySet()) {
            totalItemCount += orderItems.get(menu);
        }

        if (totalItemCount > 20) {
            throw new IllegalArgumentException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        }
    }
}
