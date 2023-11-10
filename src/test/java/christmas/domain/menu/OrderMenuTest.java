package christmas.domain.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
        long expectedPrice = items.stream().mapToLong(MenuAmount::getTotalPrice).sum();
        // when
        long totalPrice = orderMenu.getTotalPrice();
        // then
        assertThat(totalPrice).isEqualTo(expectedPrice);
    }
}