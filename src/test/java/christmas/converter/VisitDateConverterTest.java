package christmas.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateConverterTest {

    @DisplayName("입력한 방문 날짜가 숫자가 아닌 경우에 대한 예외 처리")
    @ValueSource(strings = {"1a", "!@#", " "})
    @ParameterizedTest
    void validateDateDigit(String inputDate) {
        VisitDateConverter visitDateConverter = new VisitDateConverter();

        assertThatThrownBy(() -> visitDateConverter.convertToInt(inputDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}