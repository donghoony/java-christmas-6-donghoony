package christmas.domain.event;

import christmas.domain.Money;
import christmas.domain.event.giveaway.GiveawayProduct;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveawayEventTest {

    @Test
    @DisplayName("증정 이벤트에 따른 혜택 내역을 정확하게 반환한다.")
    public void giveawayEventTest() {
        // given
        OrderMenu orderMenu = new OrderMenu(
                List.of(
                        new MenuAmount(Menu.ICE_CREAM, 1)
                )
        );
        GiveawayEvent giveaway = new GiveawayEvent(
                "증정 이벤트",
                (o) -> new GiveawayProduct("사은품", Money.of(5_000L)),
                (date) -> true);
        // when
        EventBenefitDetail benefitDetail = giveaway.getBenefitDetail(orderMenu);
        // then
        Assertions.assertThat(benefitDetail.toString()).isEqualTo("증정 이벤트: -5,000원");
    }
}