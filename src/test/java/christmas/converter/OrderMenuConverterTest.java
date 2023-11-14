package christmas.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenuConverterTest {

    OrderMenuConverter orderMenuConverter = new OrderMenuConverter();

    @DisplayName("문자열로 받은 주문 메뉴를 Map으로 변환한다.")
    @Test
    void convertToMap() {
        String input = "타파스-1,제로콜라-2";

        Map<String, Integer> orderItemInfo = orderMenuConverter.convertToMap(input);

        assertThat(orderItemInfo.containsKey("타파스")).isTrue();
        assertThat(orderItemInfo.containsKey("제로콜라")).isTrue();
        assertThat(orderItemInfo.get("타파스")).isEqualTo(1);
        assertThat(orderItemInfo.get("제로콜라")).isEqualTo(2);
    }

    @DisplayName("메뉴 형식이 예시와 다른 경우에 대한 예외 처리")
    @ValueSource(strings = {"타파스-1/제로콜라-2", "타파스=1,제로콜라=2", "타파스-1, 제로콜라-2", "타파스 - 1,제로콜라 - 2"})
    @ParameterizedTest
    void convertToMapByWrongForm(String input) {
        assertThatThrownBy(() -> orderMenuConverter.convertToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복 메뉴를 입력한 경우에 대한 예외 처리")
    @Test
    void convertToMapByDuplicateMenu() {
        String input = "타파스-1,제로콜라-2,타파스-3";

        assertThatThrownBy(() -> orderMenuConverter.convertToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴의 개수가 1이 아닌 경우에 대한 예외 처리")
    @ValueSource(strings = {"타파스-1,제로콜라-a", "타파스-1a,제로콜라-2a", "타파스-!,제로콜라-@"})
    @ParameterizedTest
    void convertToMapByNotNumberMenuCount(String input) {
        assertThatThrownBy(() -> orderMenuConverter.convertToMap(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}