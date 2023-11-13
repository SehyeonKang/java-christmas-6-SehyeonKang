package christmas.view;

import christmas.domain.Menu;
import christmas.domain.OrderMenu;

import java.util.Map;

public class OutputView {

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
}
