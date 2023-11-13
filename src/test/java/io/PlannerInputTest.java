package io;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import java.io.ByteArrayInputStream;
import java.time.YearMonth;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlannerInputTest {

    public void setupInputStream(String inputString) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(byteArrayInputStream);
    }

    PlannerInput input = new PlannerConsoleInput();

    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "-1", "123", "\n"})
    @DisplayName("올바른 날짜가 아닌 경우 예외를 발생한다.")
    public void invalidDate(String dayInput) {
        // given
        setupInputStream(dayInput);
        YearMonth month = YearMonth.of(2023, 12);
        // when, then
        assertThatThrownBy(() -> input.readDate(month))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "13", "5"})
    @DisplayName("올바른 날짜인 경우 올바르게 파싱한다.")
    public void validDate(String dayInput) {
        // given
        setupInputStream(dayInput);
        YearMonth month = YearMonth.of(2023, 12);
        // when, then
        assertDoesNotThrow(() -> input.readDate(month));
    }

    @ParameterizedTest
    @ValueSource(strings = {"없어-1,메뉴-2"})
    @DisplayName("존재하지 않는 메뉴인 경우 예외를 발생한다.")
    public void invalidMenuName(String orders) {
        // given
        setupInputStream(orders);
        // when, then
        assertThatThrownBy(() -> input.readOrders())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-0,제로콜라-0"})
    @DisplayName("개수가 1 이상이 아닌 경우 예외를 발생한다.")
    public void invalidMenuAmount(String orders) {
        // given
        setupInputStream(orders);
        // when, then
        assertThatThrownBy(() -> input.readOrders())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1?,제로콜라-x"})
    @DisplayName("개수가 숫자로 이루어지지 않은 경우 예외를 발생한다.")
    public void amountNotInteger(String orders) {
        // given
        setupInputStream(orders);
        // when, then
        assertThatThrownBy(() -> input.readOrders())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,,제로콜라-2"})
    @DisplayName("메뉴 사이 구분자가 올바르지 않은 경우 예외를 발생한다.")
    public void invalidOrderSeparator(String orders) {
        // given
        setupInputStream(orders);
        // when, then
        assertThatThrownBy(() -> input.readOrders())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"타파스=1,제로콜라=2"})
    @DisplayName("메뉴와 개수 사이 구분자가 올바르지 않은 경우 예외를 발생한다.")
    public void invalidMenuAmountSeparator(String orders) {
        // given
        setupInputStream(orders);
        // when, then
        assertThatThrownBy(() -> input.readOrders())
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("올바른 주문을 정확하게 변환한다.")
    public void invalidMenuAmountSeparator() {
        // given
        setupInputStream("해산물파스타-2,레드와인-1,초코케이크-1");
        // when, then
        List<MenuAmount> menuAmounts = input.readOrders();
        Assertions.assertThat(menuAmounts).hasSameElementsAs(
                List.of(
                        new MenuAmount(Menu.SEAFOOD_PASTA, 2),
                        new MenuAmount(Menu.RED_WINE, 1),
                        new MenuAmount(Menu.CHOCOLATE_CAKE, 1)
                )
        );
    }


    @AfterEach
    public void tearDown() {
        Console.close();
    }
}