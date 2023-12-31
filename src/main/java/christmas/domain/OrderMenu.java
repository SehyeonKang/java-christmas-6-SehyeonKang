package christmas.domain;

import christmas.exception.ExceptionMessage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OrderMenu {

    private static final int DEFAULT_COUNT_ZERO = 0;
    private static final int MINIMUM_POSSIBLE_MENU_COUNT = 1;
    private static final int MAXIMUM_POSSIBLE_MENU_COUNT = 20;

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
        int beverageCount = DEFAULT_COUNT_ZERO;
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
        int totalItemCount = DEFAULT_COUNT_ZERO;
        for (Menu menu : orderItems.keySet()) {
            totalItemCount += orderItems.get(menu);
        }

        if (totalItemCount < MINIMUM_POSSIBLE_MENU_COUNT) {
            ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
        }

        if (totalItemCount > MAXIMUM_POSSIBLE_MENU_COUNT) {
            ExceptionMessage.LIMIT_EXCEEDED_ORDER.throwIllegalArgumentException();
        }
    }

    public int calculateTotalOrderAmount() {
        int totalOrderAmount = DEFAULT_COUNT_ZERO;

        for (Menu menu : orderItems.keySet()) {
            int orderCount = orderItems.get(menu);
            totalOrderAmount += menu.calculateOrderAmount(orderCount);
        }

        return totalOrderAmount;
    }

    public int countDessertItems() {
        int dessertCount = DEFAULT_COUNT_ZERO;

        for (Menu menu : orderItems.keySet()) {
            if (MenuGroup.findByMenu(menu) == MenuGroup.DESSERT) {
                dessertCount += orderItems.get(menu);
            }
        }

        return dessertCount;
    }

    public int countMainItems() {
        int mainCount = DEFAULT_COUNT_ZERO;

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