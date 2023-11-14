package christmas.domain;

import java.util.Arrays;

public enum EventBadge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("스타", 5_000),
    EMPTY("없음", 0);

    private final String name;
    private final int minPrice;

    EventBadge(String name, int minPrice) {
        this.name = name;
        this.minPrice = minPrice;
    }

    public static EventBadge findEventBadge(int totalBenefitAmount) {
        return Arrays.stream(EventBadge.values())
                .filter(eventBadge -> eventBadge.minPrice <= totalBenefitAmount)
                .findFirst()
                .orElse(EMPTY);
    }

    public String getName() {
        return name;
    }
}