package christmas;

import christmas.domain.GiveawayProducts;
import christmas.domain.Money;
import christmas.domain.badge.Badge;
import christmas.domain.event.TotalEventBenefitDetails;
import christmas.domain.menu.OrderMenu;
import christmas.io.PlannerInput;
import christmas.io.PlannerOutput;
import christmas.service.BadgeService;
import christmas.service.EventService;
import java.time.LocalDate;
import java.time.YearMonth;

public class EventPlanner implements ExceptionLoopClient {
    private final EventService eventService;
    private final BadgeService badgeService;
    private final PlannerInput input;
    private final PlannerOutput output;
    private final YearMonth yearMonth;

    public EventPlanner(EventService eventService, BadgeService badgeService,
                        PlannerInput input, PlannerOutput output, YearMonth yearMonth) {
        this.eventService = eventService;
        this.badgeService = badgeService;
        this.input = input;
        this.output = output;
        this.yearMonth = yearMonth;
    }

    public void planEvent() {
        LocalDate today = getToday();
        OrderMenu orderMenu = readOrder();
        Money totalPrice = orderMenu.getTotalPrice();
        TotalEventBenefitDetails totalBenefits = eventService.apply(today, orderMenu);

        output.printAbstractIntroduction(today.getMonthValue(), today.getDayOfMonth());
        output.printOrderMenu(orderMenu);

        checkoutPayment(totalPrice, totalBenefits);
        receiveBadge(totalBenefits);
    }

    private void receiveBadge(TotalEventBenefitDetails totalBenefits) {
        Money totalBenefitPrice = totalBenefits.getTotalBenefitAmount();
        Badge badge = badgeService.getBadgeByTotalBenefit(totalBenefitPrice);
        output.printEventBadge(yearMonth.getMonthValue(), badge);
    }

    private void checkoutPayment(Money totalPrice, TotalEventBenefitDetails totalBenefits) {
        Money totalBenefitPrice = totalBenefits.getTotalBenefitAmount();
        Money totalPaymentPrice = totalPrice.add(totalBenefits.getTotalBenefitAmountWithoutGiveawayProducts());
        GiveawayProducts giveawayProducts = totalBenefits.getGiveawayProducts();

        output.printTotalPriceBeforeDiscount(totalPrice);
        output.printGiveawayProducts(giveawayProducts);
        output.printTotalBenefits(totalBenefits);
        output.printTotalBenefitAmount(totalBenefitPrice);
        output.printTotalPriceAfterDiscount(totalPaymentPrice);
    }

    private LocalDate getToday() {
        int month = yearMonth.getMonthValue();
        int day = readDate();
        return LocalDate.of(yearMonth.getYear(), month, day);
    }

    private int readDate() {
        output.askExpectedDay(yearMonth.getMonthValue());
        return repeatUntilValid(() -> input.readDate(yearMonth));
    }

    private OrderMenu readOrder() {
        output.askOrder();
        return repeatUntilValid(input::readOrders);
    }
}
