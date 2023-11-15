package christmas.domain;

import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static christmas.exception.ChristmasException.MENU_COUNT_OVER_TWENTY_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    @Test
    @DisplayName("메뉴의 개수가 20개를 초과하면 예외가 발생한다.")
    void givenMenusOverTwenty_ThenFail(){

        Map<String, Integer> order = new HashMap<>();
        order.put("해산물파스타", 10);
        order.put("티본스테이크", 15);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            InputView.validateMenuCountOverTwenty(order);
        });

        assertEquals(MENU_COUNT_OVER_TWENTY_ERROR.getMessage(), exception.getMessage());
    }
}
