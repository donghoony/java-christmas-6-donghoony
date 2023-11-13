package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Beneficial;
import christmas.domain.Money;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TotalDiscountTest {

    @Test
    @DisplayName("총 주문 할인이 정상적으로 적용된다.")
    public void totalAmountDiscount() {
        // given
        OrderMenu orderMenu = new OrderMenu(
                List.of(
                        new MenuAmount(Menu.BARBEQUE_RIB, 1),
                        new MenuAmount(Menu.ICE_CREAM, 1)
                )
        );
        // when
        Money expectedDiscountAmount = Money.of(1_000L);
        TotalDiscount totalDiscount = new TotalDiscount(expectedDiscountAmount);
        Beneficial discountedAmount = totalDiscount.apply(orderMenu);
        // then
        assertThat(discountedAmount.getPrice()).isEqualTo(expectedDiscountAmount);
    }
}