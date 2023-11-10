package christmas.domain.event.giveaway;

import christmas.domain.Money;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuGiveawayTest {
    @Test
    @DisplayName("메뉴를 증정하면, 해당 메뉴의 가격만큼의 혜택을 반환한다.")
    public void getGiveawayBenefitTest() {
        // given
        MenuAmount giveAwayMenuAmount = new MenuAmount(Menu.BARBEQUE_RIB, 3);
        MenuGiveaway menuGiveaway = new MenuGiveaway(giveAwayMenuAmount);
        Money expectedBenefit = Menu.BARBEQUE_RIB.getPrice().multiply(3L);
        // when
        Money benefitAmount = menuGiveaway.getBenefitAmount();
        // then
        Assertions.assertThat(benefitAmount).isEqualTo(expectedBenefit);
    }
}