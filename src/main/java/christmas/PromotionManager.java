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

        BenefitResult benefitResult = new BenefitResult();
        int discountAmount = benefitResult.applyDiscountEvents(orderAmount, visitDate, orderMenu);

        announceGiftMenu(benefitResult, orderAmount);
        announceBenefitResults(benefitResult, orderAmount,discountAmount);
    }

    private void announceGiftMenu(BenefitResult benefitResult, OrderAmount orderAmount) {
        Menu giftMenu = benefitResult.applyGiftBenefit(orderAmount);

        if (giftMenu == Menu.EMPTY) {
            outputView.printGiftMenu(giftMenu, 0);
            return;
        }
        outputView.printGiftMenu(giftMenu, 1);
    }

    private void announceBenefitResults(BenefitResult benefitResult, OrderAmount orderAmount, int discountAmount) {
        int discountedAmount = orderAmount.calculateDiscountedAmount(discountAmount);
        int totalBenefitAmount = benefitResult.calculateTotalBenefitAmount();
        EventBadge eventBadge = EventBadge.findEventBadge(totalBenefitAmount);

        outputView.printBenefitResult(benefitResult);
        outputView.printTotalBenefitAmount(totalBenefitAmount);
        outputView.printDiscountedAmount(discountedAmount);
        outputView.printEventBadge(eventBadge);
    }
}