package christmas;

import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PromotionManager promotionManager = new PromotionManager(inputView, outputView);

        promotionManager.start();
    }
}
