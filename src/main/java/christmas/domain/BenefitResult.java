package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BenefitResult {

    private final Map<DiscountEvent, Integer> benefitResult;

    public BenefitResult() {
        benefitResult = new LinkedHashMap<>();
    }

    public int applyChristMasDDayDiscount(DiscountEvent discountEvent) {
        if (discountEvent == DiscountEvent.CHRISTMAS_D_DAY) {
            System.out.println("확인 완료");
        }
        return 0;
    }
}
