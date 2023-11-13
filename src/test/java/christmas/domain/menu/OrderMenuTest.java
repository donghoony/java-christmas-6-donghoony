package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.Money;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {
    @Test
    @DisplayName("메뉴의 총합을 정확하게 계산한다.")
    public void calculateTotalPrice() {
        // given
        List<MenuAmount> items = List.of(
                new MenuAmount(Menu.T_BONE_STEAK, 3),
                new MenuAmount(Menu.CHOCOLATE_CAKE, 2),
                new MenuAmount(Menu.ICE_CREAM, 5)
        );
        OrderMenu orderMenu = new OrderMenu(items);
        Money expectedPrice = items.stream()
                .map(MenuAmount::getPrice)
                .reduce(Money.of(0L), Money::add);
        // when
        Money totalPrice = orderMenu.getTotalPrice();
        // then
        assertThat(totalPrice).isEqualTo(expectedPrice);
    }

    @Test
    @DisplayName("메뉴와 개수를 기반으로 주문 리스트를 생성한다.")
    public void properOrderTest() {
        // given
        List<MenuAmount> orders = List.of(
                new MenuAmount(Menu.CHAMPAGNE, 5),
                new MenuAmount(Menu.TAPAS, 2),
                new MenuAmount(Menu.T_BONE_STEAK, 13)
        );
        // when, then
        Assertions.assertDoesNotThrow(() -> new OrderMenu(orders));
    }

    @Test
    @DisplayName("음료 외 메뉴를 주문하지 않으면, 예외를 발생한다.")
    public void onlyBeverageTest() {
        // given
        List<MenuAmount> orders = List.of(
                new MenuAmount(Menu.CHAMPAGNE, 5),
                new MenuAmount(Menu.RED_WINE, 1),
                new MenuAmount(Menu.ZERO_COKE, 3)
        );
        // when, then
        assertThatThrownBy(() -> new OrderMenu(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("한 번에 20개보다 많은 메뉴를 주문하면, 예외를 발생한다.")
    public void tooMuchOrderTest() {
        // given
        List<MenuAmount> orders = List.of(
                new MenuAmount(Menu.TAPAS, 10),
                new MenuAmount(Menu.RED_WINE, 6),
                new MenuAmount(Menu.ICE_CREAM, 5)
        );
        // when, then
        assertThatThrownBy(() -> new OrderMenu(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 메뉴를 주문하면, 예외를 발생한다.")
    public void duplicateOrderTest() {
        // given
        List<MenuAmount> orders = List.of(
                new MenuAmount(Menu.TAPAS, 10),
                new MenuAmount(Menu.TAPAS, 1)
        );
        // when, then
        assertThatThrownBy(() -> new OrderMenu(orders))
                .isInstanceOf(IllegalArgumentException.class);
    }
}