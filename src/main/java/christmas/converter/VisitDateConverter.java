package christmas.converter;

import christmas.exception.ExceptionMessage;

public class VisitDateConverter {

    public int convertToInt(String inputDate) {
        validateDateDigit(inputDate);
        return Integer.parseInt(inputDate);
    }

    private void validateDateDigit(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            ExceptionMessage.INVALID_DATE.throwIllegalArgumentException();
        }
    }
}
