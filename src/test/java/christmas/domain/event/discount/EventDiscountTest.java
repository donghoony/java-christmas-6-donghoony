package christmas.domain.event.discount;

import christmas.domain.Money;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.event.EventDiscount;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventDiscountTest {

    @Test
    @DisplayName("할인, 일정, 이벤트 이름을 바탕으로 할인 정보를 반환한다.")
    public void categoryDiscountTest() {
        // given
        OrderMenu orderMenu = new OrderMenu(
                List.of(
                        new MenuAmount(Menu.ICE_CREAM, 1)
                )
        );
        EventDiscount alwaysDiscount = new EventDiscount("무조건 천원 할인", (orders -> Money.of(1_000L)), (date) -> true);

        // when
        EventBenefitDetail eventBenefitDetail = alwaysDiscount.getBenefitDetail(orderMenu);
        // then
        Assertions.assertThat(eventBenefitDetail.toString()).isEqualTo("무조건 천원 할인: 1,000원");
    }
}