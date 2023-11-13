package christmas.converter;

import christmas.exception.ExceptionMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMenuConverter {

    public Map<String, Integer> convertToMap(String inputOrderMenu) {
        Map<String, Integer> orderedItemInfo = new HashMap<>();
        List<String> orderedItems = Arrays.asList(inputOrderMenu.split(","));

        return createOrderedItems(orderedItemInfo, orderedItems);
    }

    private Map<String, Integer> createOrderedItems(Map<String, Integer> orderedItemInfo, List<String> orderedItems) {
        for (String orderedItem : orderedItems) {
            List<String> itemParts = Arrays.asList(orderedItem.split("-"));
            validateOrderFormat(itemParts);

            String menuName = itemParts.get(0);
            int count = convertToInt(itemParts.get(1));

            validateDuplicateOrder(orderedItemInfo, itemParts.get(0));
            orderedItemInfo.put(menuName, count);
        }

        return orderedItemInfo;
    }

    private void validateOrderFormat(List<String> itemParts) {
        if (itemParts.size() == 1) {
            ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
        }
    }

    private void validateDuplicateOrder(Map<String, Integer> orderedItemInfo, String menuName) {
        if (orderedItemInfo.containsKey(menuName)) {
            ExceptionMessage.DUPLICATE_ORDER.throwIllegalArgumentException();
        }
    }

    private int convertToInt(String itemCount) {
        try {
            return Integer.parseInt(itemCount);
        } catch (NumberFormatException e) {
            ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
            return 0;
        }
    }
}
