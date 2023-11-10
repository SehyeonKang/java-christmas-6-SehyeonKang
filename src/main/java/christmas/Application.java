package christmas;

import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        PromotionManager promotionManager = new PromotionManager(inputView);

        promotionManager.startPromotion();
    }
}
