package christmas;

import christmas.domain.*;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.*;

public class PromotionManager {

    private final InputView inputView;
    private final OutputView outputView;

    public PromotionManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        VisitDate visitDate = saveVisitDate();
        OrderMenu orderMenu = saveOrderMenu();
        runPromotion(visitDate, orderMenu);
    }

    private VisitDate saveVisitDate() {
        while (true) {
            try {
                String inputDate = inputView.readVisitDate();
                int visitDay = Integer.parseInt(inputDate);
                return new VisitDate(visitDay);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private OrderMenu saveOrderMenu() {
        while (true) {
            try {
                String inputOrderMenu = inputView.readOrderMenu();
                Map<String, Integer> orderedItemInfo = convertToMap(inputOrderMenu);
                return new OrderMenu(orderedItemInfo);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<String, Integer> convertToMap(String inputOrderMenu) {
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

    private void runPromotion(VisitDate visitDate, OrderMenu orderMenu) {
        outputView.printPromotionStartText();
        outputView.printOrderMenu(orderMenu);

        OrderAmount orderAmount = new OrderAmount(orderMenu.calculateTotalOrderAmount());
        outputView.printTotalOrderAmount(orderAmount);

        announceGiftMenu(orderAmount);
        announceBenefitResult(orderAmount, orderMenu, visitDate);
    }

    private void announceGiftMenu(OrderAmount orderAmount) {
        Menu giftMenu = orderAmount.applyGiftBenefit();

        if (giftMenu == Menu.EMPTY) {
            outputView.printGiftMenu(giftMenu, 0);
            return;
        }
        outputView.printGiftMenu(giftMenu, 1);
    }

    private void announceBenefitResult(OrderAmount orderAmount, OrderMenu orderMenu, VisitDate visitDate) {
        int discountedAmount = orderAmount.applyDiscount(visitDate, orderMenu);
        int totalBenefitAmount = orderAmount.calculateTotalBenefitAmount();

        outputView.printBenefitResult(orderAmount);
        outputView.printTotalBenefitAmount(totalBenefitAmount);

        EventBadge eventBadge = EventBadge.findEventBadge(totalBenefitAmount);
        System.out.println(eventBadge.name());
    }
}