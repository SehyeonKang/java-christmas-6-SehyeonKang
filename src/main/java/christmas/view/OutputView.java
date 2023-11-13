package christmas.view;

import christmas.domain.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    private NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

    public void printErrorMessage(String message) {
        System.out.println(message + "\n");
    }

    public void printPromotionStartText() {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printOrderMenu(OrderMenu orderMenu) {
        Map<Menu, Integer> orderItems = orderMenu.getOrderItems();

        System.out.println("\n<주문 메뉴>");
        for (Menu menu : orderItems.keySet()) {
            System.out.println(menu.getName() + " " + orderItems.get(menu) + "개");
        }
    }

    public void printTotalOrderAmount(OrderAmount orderAmount) {
        String totalOrderAmount = numberFormat.format(orderAmount.getTotalOrderAmount());

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(totalOrderAmount + "원");
    }

    public void printGiftMenu(Menu giftMenu, int count) {
        System.out.println("\n<증정 메뉴>");

        if (count == 0) {
            System.out.println(giftMenu.getName());
            return;
        }

        System.out.println(giftMenu.getName() + " " + count + "개");
    }

    public void printBenefitResult(OrderAmount orderAmount) {
        BenefitResult benefitResult = orderAmount.getBenefitResult();
        Map<DiscountEvent, Integer> result = benefitResult.getBenefitResult();

        System.out.println("\n<혜택 내역>");
        for (DiscountEvent event : result.keySet()) {
            String discountAmount = numberFormat.format(result.get(event));
            System.out.println(event.getName() + ": -" + discountAmount + "원");
        }

        if (result.size() == 0) {
            System.out.println("없음");
        }
    }

    public void printTotalBenefitAmount(int totalBenefitAmount) {
        String formattedTotalBenefitAmount = numberFormat.format(totalBenefitAmount);

        System.out.println("\n<총혜택 금액>");
        System.out.println("-" + formattedTotalBenefitAmount + "원");
    }

    public void printDiscountedAmount(int discountedAmount) {
        String formattedDiscountedAmount = numberFormat.format(discountedAmount);

        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(formattedDiscountedAmount + "원");
    }

    public void printEventBadge(EventBadge eventBadge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.print(eventBadge.getName());
    }
}
