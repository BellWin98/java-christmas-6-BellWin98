package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.exception.ChristmasException.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatorTest {

    @Test
    @DisplayName("입력된 날짜가 없으면 예외가 발생한다.")
    void givenEmptyDate_ThenFail() {
        String date = "";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateDate(date);
        });

        assertEquals(DATE_IS_EMPTY_ERROR.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("입력된 날짜가 정수가 아니면 예외가 발생한다.")
    @ValueSource(strings = {"날짜", "!@#$"})
    void givenNotIntegerDate_ThenFail(final String date) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateDate(date);
        });

        assertEquals(DATE_IS_INVALID_ERROR.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("입력된 날짜가 1 이상 31 이하가 아니면 예외가 발생한다.")
    @ValueSource(strings = {"0", "32"})
    void givenDateNotInRange_ThenFail(final String date) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateDate(date);
        });
        assertEquals(DATE_IS_INVALID_ERROR.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("입력된 날짜가 1 이상 31 이하이면 예외가 발생하지 않는다.")
    @ValueSource(strings = {"1", "30"})
    void givenDateInRange_ThenSuccess(final String date) {
        Validator.validateDate(date);
    }

    @Test
    @DisplayName("입력된 메뉴가 없으면 예외가 발생한다.")
    void givenEmptyMenu_ThenFail() {
        String menu = "";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateMenu(menu);
        });
        assertEquals(MENU_IS_INVALID_ERROR.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("입력된 메뉴가 존재하지 않는 메뉴이면 예외가 발생한다.")
    @ValueSource(strings = {"사이다", "제육볶음"})
    void givenMenuNotContain_ThenFail(final String menu) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateMenu(menu);
        });
        assertEquals(MENU_IS_INVALID_ERROR.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("메뉴의 개수가 입력되지 않으면 예외가 발생한다.")
    void givenEmptyMenuCount_ThenFail() {
        String count = "";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateCountOfMenu(count);
        });
        assertEquals(MENU_IS_INVALID_ERROR.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("메뉴 개수가 정수가 아니면 예외가 발생한다.")
    @ValueSource(strings = {"메뉴1개", "!@#$", "3.14"})
    void givenMenuCountNotInteger_ThenFail(final String count) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateCountOfMenu(count);
        });

        assertEquals(MENU_IS_INVALID_ERROR.getMessage(), exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("메뉴 개수가 1 이상 20 이하가 아니면 예외가 발생한다.")
    @ValueSource(strings = {"0", "21"})
    void givenMenuCountNotInRange_ThenFail(final String count) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Validator.validateCountOfMenu(count);
        });

        assertEquals(MENU_IS_INVALID_ERROR.getMessage(), exception.getMessage());
    }
}
