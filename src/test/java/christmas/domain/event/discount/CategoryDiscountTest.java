package christmas.domain.event.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Beneficial;
import christmas.domain.Money;
import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryDiscountTest {
    @Test
    @DisplayName("카테고리별 할인가를 올바르게 계산한다.")
    public void categoryDiscountTest() {
        // given
        OrderMenu orderMenu = new OrderMenu(
                List.of(
                        new MenuAmount(Menu.BARBEQUE_RIB, 5),
                        new MenuAmount(Menu.ICE_CREAM, 3)
                )
        );
        // when
        Money discountPerDish = Money.of(1_000L);
        Money expectedDiscountAmount = Money.of(5_000L);
        CategoryDiscount categoryDiscount = new CategoryDiscount(Category.MAIN_DISH, discountPerDish);
        LocalDate today = LocalDate.of(2023, 12, 1);
        Beneficial discountedAmount = categoryDiscount.apply(today, orderMenu);
        // then
        assertThat(discountedAmount.getPrice()).isEqualTo(expectedDiscountAmount);
    }

    @Test
    @DisplayName("카테고리에 해당하는 음식이 없는 경우, 할인되지 않는다")
    public void noCategoryExistsTest() {
        // given
        OrderMenu orderMenu = new OrderMenu(
                List.of(
                        new MenuAmount(Menu.BARBEQUE_RIB, 5),
                        new MenuAmount(Menu.ICE_CREAM, 3)
                )
        );
        Money discountPerDish = Money.of(1_000L);
        Money expectedDiscountAmount = Money.of(0L);
        CategoryDiscount categoryDiscount = new CategoryDiscount(Category.BEVERAGE, discountPerDish);
        LocalDate today = LocalDate.of(2023, 12, 1);

        //when
        Beneficial discountedAmount = categoryDiscount.apply(today, orderMenu);
        // then
        assertThat(discountedAmount.getPrice()).isEqualTo(expectedDiscountAmount);
    }
}
