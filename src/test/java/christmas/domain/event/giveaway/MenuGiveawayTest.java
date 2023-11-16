package christmas.domain.event.giveaway;

import christmas.domain.Beneficial;
import christmas.domain.Money;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuGiveawayTest {
    @Test
    @DisplayName("메뉴를 증정하면, 해당 메뉴의 가격만큼의 혜택을 반환한다.")
    public void getGiveawayBenefitTest() {
        // given
        MenuGiveaway menuGiveaway = new MenuGiveaway(new MenuAmount(Menu.BARBEQUE_RIB, 1));
        Money expectedBenefit = Menu.BARBEQUE_RIB.getPrice();
        LocalDate today = LocalDate.of(2023, 12, 1);
        // when
        Beneficial benefit = menuGiveaway.apply(today, new OrderMenu(
                List.of(
                        new MenuAmount(Menu.SEAFOOD_PASTA, 1)
                )
        ));
        // then
        Assertions.assertThat(benefit.getPrice()).isEqualTo(expectedBenefit);
    }
}
