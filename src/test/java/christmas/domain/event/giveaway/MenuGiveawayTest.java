package christmas.domain.event.giveaway;

import christmas.domain.Money;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuGiveawayTest {
    @Test
    @DisplayName("메뉴를 증정하면, 해당 메뉴의 가격만큼의 혜택을 반환한다.")
    public void getGiveawayBenefitTest() {
        // given
        MenuGiveaway menuGiveaway = new MenuGiveaway(Menu.BARBEQUE_RIB);
        Money expectedBenefit = Menu.BARBEQUE_RIB.getPrice();
        // when
        Money benefitAmount = menuGiveaway.getGiveawayProduct(new OrderMenu(List.of(new MenuAmount(Menu.TAPAS, 1)))).price();
        // then
        Assertions.assertThat(benefitAmount).isEqualTo(expectedBenefit);
    }
}