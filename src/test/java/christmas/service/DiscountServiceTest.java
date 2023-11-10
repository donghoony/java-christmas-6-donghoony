package christmas.service;

import christmas.domain.Money;
import christmas.domain.event.DiscountEvent;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.event.discount.CategoryDiscount;
import christmas.domain.event.discount.TotalDiscount;
import christmas.domain.event.eventdate.RangeEventDate;
import christmas.domain.event.eventdate.SpecificEventDate;
import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {

    @Test
    @DisplayName("주문 내역과 할인 내역을 토대로, 올바르게 주문 금액을 할인한다.")
    public void discountTest() {
        // given
        LocalDate today = LocalDate.of(2023, 12, 3);
        LocalDate startOfDecember = LocalDate.of(2023, 12, 1);
        LocalDate endOfDecember = LocalDate.of(2023, 12, 31);
        List<DayOfWeek> weekdays = List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);
        List<DayOfWeek> weekends = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        List<LocalDate> eventDays = List.of(
                LocalDate.of(2023, 12, 3),
                LocalDate.of(2023, 12, 10),
                LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 10, 24),
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 31)
        );
        OrderMenu orderMenu = new OrderMenu(List.of(
                new MenuAmount(Menu.T_BONE_STEAK, 1),
                new MenuAmount(Menu.BARBEQUE_RIB, 1),
                new MenuAmount(Menu.CHOCOLATE_CAKE, 2),
                new MenuAmount(Menu.ZERO_COKE, 1)
        ));

        DiscountService discountService = new DiscountService(
                today,
                List.of(
                        new DiscountEvent(
                                "크리스마스 디데이 할인",
                                (o) -> {
                                    int diff = today.getDayOfMonth() - startOfDecember.getDayOfMonth();
                                    return Money.of(-1000 - 100 * diff);
                                },
                                new RangeEventDate(startOfDecember, endOfDecember)),
                        new DiscountEvent("평일 할인",
                                new CategoryDiscount(Category.DESSERT, Money.of(-2_023L)),
                                new RangeEventDate(startOfDecember, endOfDecember, weekdays)
                        ),
                        new DiscountEvent("주말 할인",
                                new CategoryDiscount(Category.MAIN_DISH, Money.of(-2_023L)),
                                new RangeEventDate(startOfDecember, endOfDecember, weekends)
                        ),
                        new DiscountEvent("특별 할인",
                                new TotalDiscount(Money.of(-1_000L)),
                                new SpecificEventDate(eventDays)
                        )
                )
        );
        // when
        List<EventBenefitDetail> eventBenefitDetails = discountService.applyDiscounts(orderMenu);
        List<Money> discountAmounts = eventBenefitDetails.stream()
                .map(EventBenefitDetail::getDiscountAmount).toList();
        Money totalDiscountAmount = discountAmounts.stream().reduce(Money.of(0L), Money::add);
        // then
        Assertions.assertThat(eventBenefitDetails).hasSize(3);
        Assertions.assertThat(discountAmounts).hasSameElementsAs(
                List.of(Money.of(-1_200L), Money.of(-4_046L), Money.of(-1_000L))
        );
        Assertions.assertThat(totalDiscountAmount).isEqualTo(Money.of(-31_246L + 25_000L));
    }
}