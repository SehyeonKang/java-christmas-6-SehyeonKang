package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class OrderMenu {

    private final Map<Menu, Integer> orderItems;

    public OrderMenu(Map<String, Integer> orderedItems) {
        orderItems = new HashMap<>();
        saveOrderedItems(orderedItems);
    }

    private void saveOrderedItems(Map<String, Integer> orderedItems) {
        for (String menuName : orderedItems.keySet()) {
            Menu menu = Menu.findByMenuName(menuName);
            orderItems.put(menu, orderedItems.get(menuName));
        }
    }
}
