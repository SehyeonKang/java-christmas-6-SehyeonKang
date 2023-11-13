package christmas.domain;

import christmas.exception.ExceptionMessage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderMenu {

    private final Map<Menu, Integer> orderItems;

    public OrderMenu(Map<String, Integer> orderedItems) {
        orderItems = new HashMap<>();
        saveOrderedItems(orderedItems);

        validateOrderAgainstMenu();
        validateOnlyBeverage();
        validateTotalItemCount();
    }

    private void saveOrderedItems(Map<String, Integer> orderedItems) {
        for (String menuName : orderedItems.keySet()) {
            Menu menu = Menu.findByMenuName(menuName);
            orderItems.put(menu, orderedItems.get(menuName));
        }
    }

    private void validateOrderAgainstMenu() {
        for (Menu menu : orderItems.keySet()) {
            if (menu == Menu.EMPTY) {
                ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
            }
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
            ExceptionMessage.ONLY_BEVERAGE.throwIllegalArgumentException();
        }
    }

    private void validateTotalItemCount() {
        int totalItemCount = 0;
        for (Menu menu : orderItems.keySet()) {
            totalItemCount += orderItems.get(menu);
        }

        if (totalItemCount < 1) {
            ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
        }

        if (totalItemCount > 20) {
            ExceptionMessage.LIMIT_EXCEEDED_ORDER.throwIllegalArgumentException();
        }
    }

    public int calculateTotalOrderAmount() {
        int totalOrderAmount = 0;

        for (Menu menu : orderItems.keySet()) {
            int orderCount = orderItems.get(menu);
            totalOrderAmount += menu.calculateOrderAmount(orderCount);
        }

        return totalOrderAmount;
    }

    public int countDessertItems() {
        int dessertCount = 0;

        for (Menu menu : orderItems.keySet()) {
            if (MenuGroup.findByMenu(menu) == MenuGroup.DESSERT) {
                dessertCount += orderItems.get(menu);
            }
        }

        return dessertCount;
    }

    public int countMainItems() {
        int mainCount = 0;

        for (Menu menu : orderItems.keySet()) {
            if (MenuGroup.findByMenu(menu) == MenuGroup.MAIN) {
                mainCount += orderItems.get(menu);
            }
        }

        return mainCount;
    }

    public Map<Menu, Integer> getOrderItems() {
        return Collections.unmodifiableMap(orderItems);
    }
}
