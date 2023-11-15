package christmas.view;

import christmas.domain.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = "\n";
    private static final String STRING_EMPTY = " ";
    private static final String MINUS_BAR = "-";
    private static final String NAME_AMOUNT_DELIMITER = ": -";
    private static final String MENU_COUNT_MESSAGE = "개";
    private static final String AMOUNT_WON_MESSAGE = "원";
    private static final String NOTHING = "없음";
    private static final String INTRO_PROMOTION = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_ORDER_AMOUNT_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIFT_MENU_MESSAGE = "<증정 메뉴>";
    private static final String BENEFIT_RESULT_MESSAGE = "<혜택 내역>";
    private static final String TOTAL_BENEFIT_AMOUNT_MESSAGE = "<총혜택 금액>";
    private static final String DISCOUNTED_AMOUNT_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String DECEMBER_EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final int BENEFIT_ZERO = 0;

    private NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

    public void printErrorMessage(String message) {
        System.out.println(message + NEW_LINE);
    }

    public void printPromotionStartText() {
        System.out.println(INTRO_PROMOTION);
    }

    public void printOrderMenu(OrderMenu orderMenu) {
        Map<Menu, Integer> orderItems = orderMenu.getOrderItems();

        System.out.println(NEW_LINE + ORDER_MENU_MESSAGE);
        for (Menu menu : orderItems.keySet()) {
            System.out.println(menu.getName() + STRING_EMPTY + orderItems.get(menu) + MENU_COUNT_MESSAGE);
        }
    }

    public void printTotalOrderAmount(OrderAmount orderAmount) {
        String totalOrderAmount = numberFormat.format(orderAmount.getTotalOrderAmount());

        System.out.println(NEW_LINE + TOTAL_ORDER_AMOUNT_MESSAGE);
        System.out.println(totalOrderAmount + AMOUNT_WON_MESSAGE);
    }

    public void printGiftMenu(Menu giftMenu, int count) {
        System.out.println(NEW_LINE + GIFT_MENU_MESSAGE);

        if (count == BENEFIT_ZERO) {
            System.out.println(giftMenu.getName());
            return;
        }

        System.out.println(giftMenu.getName() + STRING_EMPTY + count + MENU_COUNT_MESSAGE);
    }

    public void printBenefitResult(BenefitResult benefitResult) {
        Map<DiscountEvent, Integer> result = benefitResult.getBenefitResult();

        System.out.println(NEW_LINE + BENEFIT_RESULT_MESSAGE);
        for (DiscountEvent event : result.keySet()) {
            String discountAmount = numberFormat.format(result.get(event));
            System.out.println(event.getName() + NAME_AMOUNT_DELIMITER + discountAmount + AMOUNT_WON_MESSAGE);
        }

        if (result.size() == BENEFIT_ZERO) {
            System.out.println(NOTHING);
        }
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        String formattedTotalBenefitAmount = numberFormat.format(totalBenefitAmount);

        System.out.println(NEW_LINE + TOTAL_BENEFIT_AMOUNT_MESSAGE);
        if (totalBenefitAmount == BENEFIT_ZERO) {
            System.out.println(totalBenefitAmount + AMOUNT_WON_MESSAGE);
            return;
        }
        System.out.println(MINUS_BAR + formattedTotalBenefitAmount + AMOUNT_WON_MESSAGE);
    }

    public void printDiscountedAmount(int discountedAmount) {
        String formattedDiscountedAmount = numberFormat.format(discountedAmount);

        System.out.println(NEW_LINE + DISCOUNTED_AMOUNT_MESSAGE);
        System.out.println(formattedDiscountedAmount + AMOUNT_WON_MESSAGE);
    }

    public void printEventBadge(EventBadge eventBadge) {
        System.out.println(NEW_LINE + DECEMBER_EVENT_BADGE_MESSAGE);
        System.out.print(eventBadge.getName());
    }
}