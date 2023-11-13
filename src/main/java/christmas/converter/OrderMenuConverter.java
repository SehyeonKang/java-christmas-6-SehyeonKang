package christmas.converter;

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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicateOrder(Map<String, Integer> orderedItemInfo, String menuName) {
        if (orderedItemInfo.containsKey(menuName)) {
            throw new IllegalArgumentException("[ERROR] 중복된 메뉴를 주문했습니다. 다시 입력해 주세요.");
        }
    }

    private int convertToInt(String itemCount) {
        try {
            return Integer.parseInt(itemCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
