package christmas;

import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionManager {

    private final InputView inputView;
    private final OutputView outputView;

    public PromotionManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startPromotion() {
        VisitDate visitDate = saveVisitDate();
        OrderMenu orderMenu = saveOrderMenu();
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
                Map<String, Integer> orderedItems = convertToMenuMap(inputOrderMenu);
                return new OrderMenu(orderedItems);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<String, Integer> convertToMenuMap(String input) {
        Map<String, Integer> orderedItems = new HashMap<>();
        List<String> menuItems = Arrays.asList(input.split(","));

        for (String menuItem : menuItems) {
            List<String> itemParts = Arrays.asList(menuItem.split("-"));

            String menuName = itemParts.get(0);
            int count = Integer.parseInt(itemParts.get(1));

            orderedItems.put(menuName, count);
        }

        return orderedItems;
    }
}
