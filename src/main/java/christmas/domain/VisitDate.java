package christmas.domain;

public class VisitDate {

    private final int day;

    public VisitDate(int day) {
        validateVisitDate(day);
        this.day = day;
    }

    private void validateVisitDate(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해주세요");
        }
    }
}
