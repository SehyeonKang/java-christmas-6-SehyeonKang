package christmas;

import christmas.converter.OrderMenuConverter;
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
        OrderMenuConverter orderMenuConverter = new OrderMenuConverter();

        while (true) {
            try {
                String inputOrderMenu = inputView.readOrderMenu();
                Map<String, Integer> orderedItemInfo = orderMenuConverter.convertToMap(inputOrderMenu);
                return new OrderMenu(orderedItemInfo);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void runPromotion(VisitDate visitDate, OrderMenu orderMenu) {
        outputView.printPromotionStartText();
        outputView.printOrderMenu(orderMenu);

        OrderAmount orderAmount = new OrderAmount(orderMenu.calculateTotalOrderAmount());
        outputView.printTotalOrderAmount(orderAmount);

        announceGiftMenu(orderAmount);
        announceBenefitResults(orderAmount, orderMenu, visitDate);
    }

    private void announceGiftMenu(OrderAmount orderAmount) {
        Menu giftMenu = orderAmount.applyGiftBenefit();

        if (giftMenu == Menu.EMPTY) {
            outputView.printGiftMenu(giftMenu, 0);
            return;
        }
        outputView.printGiftMenu(giftMenu, 1);
    }

    private void announceBenefitResults(OrderAmount orderAmount, OrderMenu orderMenu, VisitDate visitDate) {
        int discountedAmount = orderAmount.applyDiscount(visitDate, orderMenu);
        int totalBenefitAmount = orderAmount.calculateTotalBenefitAmount();
        EventBadge eventBadge = EventBadge.findEventBadge(totalBenefitAmount);

        outputView.printBenefitResult(orderAmount);
        outputView.printTotalBenefitAmount(totalBenefitAmount);
        outputView.printDiscountedAmount(discountedAmount);
        outputView.printEventBadge(eventBadge);
    }
}