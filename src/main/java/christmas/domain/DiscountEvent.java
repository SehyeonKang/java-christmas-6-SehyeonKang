package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DiscountEvent {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인",
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25)),
    WEEKDAY("평일 할인",
            Arrays.asList(3, 4, 5, 6, 7,
                    10, 11, 12, 13 ,14,
                    17, 18, 19, 20, 21,
                    24, 25, 26, 27, 28,
                    31)),
    WEEKEND("주말 할인",
            Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    SPECIAL("특별 할인",
            Arrays.asList(3, 10, 17, 24, 25, 31)),
    GIFT("증정 이벤트",
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));


    private final String name;
    private final List<Integer> days;

    DiscountEvent(String name, List<Integer> days) {
        this.name = name;
        this.days = days;
    }

    public static List<DiscountEvent> findByDay(int orderDay) {
        return Arrays.stream(DiscountEvent.values())
                .filter(discountEvent -> discountEvent.hasEvent(orderDay))
                .collect(Collectors.toList());

    }

    private boolean hasEvent(int orderDay) {
        return days.stream()
                .anyMatch(day -> day == orderDay);
    }

    public String getName() {
        return name;
    }
}
