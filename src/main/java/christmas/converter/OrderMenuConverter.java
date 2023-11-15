package christmas.converter;

import christmas.exception.ExceptionMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMenuConverter {

    private static final String SPLITTER_COMMA = ",";
    private static final String SPLITTER_BAR = "-";
    private static final String STRING_EMPTY = " ";
    private static final int MENU_NAME_INDEX = 0;
    private static final int TERMINATE_METHOD_ZERO = 0;
    private static final int MENU_COUNT_INDEX = 1;
    private static final int INVALID_ORDER_FORMAT_SIZE = 1;

    public Map<String, Integer> convertToMap(String inputOrderMenu) {
        Map<String, Integer> orderedItemInfo = new HashMap<>();
        List<String> orderedItems = Arrays.asList(inputOrderMenu.split(SPLITTER_COMMA));

        return createOrderedItems(orderedItemInfo, orderedItems);
    }

    private Map<String, Integer> createOrderedItems(Map<String, Integer> orderedItemInfo, List<String> orderedItems) {
        for (String orderedItem : orderedItems) {
            List<String> itemParts = Arrays.asList(orderedItem.split(SPLITTER_BAR));
            validateOrderFormat(itemParts);

            String menuName = itemParts.get(MENU_NAME_INDEX);
            int count = convertToInt(itemParts.get(MENU_COUNT_INDEX));

            validateDuplicateOrder(orderedItemInfo, itemParts.get(MENU_NAME_INDEX));
            orderedItemInfo.put(menuName, count);
        }

        return orderedItemInfo;
    }

    private void validateOrderFormat(List<String> itemParts) {
        if (itemParts.size() == INVALID_ORDER_FORMAT_SIZE) {
            ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
        }

        for (String itemPart : itemParts) {
            if (itemPart.contains(STRING_EMPTY)) {
                ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
            }
        }
    }

    private void validateDuplicateOrder(Map<String, Integer> orderedItemInfo, String menuName) {
        if (orderedItemInfo.containsKey(menuName)) {
            ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
        }
    }

    private int convertToInt(String itemCount) {
        try {
            return Integer.parseInt(itemCount);
        } catch (NumberFormatException e) {
            ExceptionMessage.INVALID_ORDER.throwIllegalArgumentException();
            return TERMINATE_METHOD_ZERO;
        }
    }
}