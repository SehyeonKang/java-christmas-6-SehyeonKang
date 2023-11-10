package christmas;

import christmas.view.InputView;

public class PromotionManager {

    private final InputView inputView;

    public PromotionManager(InputView inputView) {
        this.inputView = inputView;
    }

    public void startPromotion() {
        saveVisitDate();
    }

    private int saveVisitDate() {
        String inputDate = inputView.readVisitDate();
        return Integer.parseInt(inputDate);
    }
}
