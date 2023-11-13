package christmas;

import christmas.domain.Money;
import christmas.domain.event.PlannerEvent;
import christmas.domain.event.discount.CategoryDiscount;
import christmas.domain.event.discount.TotalDiscount;
import christmas.domain.event.eventdate.RangeEventDate;
import christmas.domain.event.eventdate.SpecificEventDate;
import christmas.domain.event.giveaway.MenuGiveaway;
import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.domain.menu.OrderMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.function.Predicate;

public class PlannerConfig {
    private final YearMonth month;
    private final Predicate<OrderMenu> eventPredicate;

    public PlannerConfig(YearMonth month, Money minimumTotalPrice) {
        this.month = month;
        eventPredicate = (orderMenu) -> orderMenu.getTotalPrice().compareTo(minimumTotalPrice) >= 0;
    }

    public List<PlannerEvent> getEvents() {
        return List.of(
                getChristmasDayEvent(),
                getWeekdayEvent(),
                getWeekendEvent(),
                getSpecialEvent(),
                getGiveawayEvent()
        );
    }

    private PlannerEvent getChristmasDayEvent() {
        return new PlannerEvent(
                "크리스마스 디데이 할인",
                (date, o) -> {
                    int diff = date.getDayOfMonth() - 1;
                    return Money.of(-1_000L - 100L * diff);
                },
                new RangeEventDate(month.atDay(1), month.atDay(25)),
                eventPredicate);
    }

    private PlannerEvent getWeekdayEvent() {
        List<DayOfWeek> weekdays = List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);

        return new PlannerEvent(
                "평일 할인",
                new CategoryDiscount(Category.DESSERT, Money.of(-2_023L)),
                new RangeEventDate(month.atDay(1), month.atEndOfMonth(), weekdays),
                eventPredicate);
    }

    private PlannerEvent getWeekendEvent() {
        List<DayOfWeek> weekends = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

        return new PlannerEvent(
                "주말 할인",
                new CategoryDiscount(Category.MAIN_DISH, Money.of(-2_023L)),
                new RangeEventDate(month.atDay(1), month.atEndOfMonth(), weekends),
                eventPredicate);
    }

    private PlannerEvent getSpecialEvent() {
        List<LocalDate> eventDays = List.of(
                LocalDate.of(2023, 12, 3),
                LocalDate.of(2023, 12, 10),
                LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 12, 24),
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 31)
        );

        return new PlannerEvent("특별 할인",
                new TotalDiscount(Money.of(-1_000L)),
                new SpecificEventDate(eventDays),
                eventPredicate);
    }

    private PlannerEvent getGiveawayEvent() {
        return new PlannerEvent(
                "증정 이벤트",
                new MenuGiveaway(
                        new MenuAmount(Menu.CHAMPAGNE, 1)
                ),
                new RangeEventDate(month.atDay(1), month.atEndOfMonth()),
                (orderMenu -> orderMenu.getTotalPrice().compareTo(Money.of(120_000L)) >= 0 &&
                                eventPredicate.test(orderMenu)));
    }

}
