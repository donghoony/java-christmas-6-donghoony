package christmas;

import christmas.domain.Money;
import christmas.domain.badge.Badge;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.menu.OrderMenu;
import christmas.io.PlannerInput;
import christmas.io.PlannerOutput;
import christmas.service.BadgeService;
import christmas.service.EventService;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class EventPlanner {
    private final EventService eventService;
    private final BadgeService badgeService;
    private final PlannerInput input;
    private final PlannerOutput output;
    private final YearMonth month;

    public EventPlanner(EventService eventService, BadgeService badgeService,
                        PlannerInput input, PlannerOutput output, YearMonth month) {
        this.eventService = eventService;
        this.badgeService = badgeService;
        this.input = input;
        this.output = output;
        this.month = month;
    }

    public void planEvent() {
        int day = readDate();

        OrderMenu orderMenu = readOrder();
        Money totalPrice = orderMenu.getTotalPrice();
        LocalDate today = LocalDate.of(2023, 12, 3);

        List<EventBenefitDetail> eventBenefitDetails = eventService.apply(today, orderMenu);

        output.printAbstractIntroduction();

        output.printOrderMenu(orderMenu);
        output.printTotalPriceBeforeDiscount(totalPrice);

        List<String> benefitProducts = eventService.getBenefitDetailsExceptMoney(today, orderMenu);
        output.printBenefitExceptMoney(benefitProducts);

        output.printTotalBenefits(eventBenefitDetails);

        Money totalBenefitPrice = eventBenefitDetails.stream().map(EventBenefitDetail::getPrice).reduce(Money.of(0L), Money::add);
        output.printTotalBenefitAmount(totalBenefitPrice);

        Money totalPaymentPrice = totalPrice.add(totalBenefitPrice);
        output.printTotalPriceAfterDiscount(totalPaymentPrice);

        Badge badge = badgeService.getBadgeByTotalBenefit(totalBenefitPrice);
        output.printEventBadge(month.getMonthValue(), badge);

    }

    private int readDate() {
        output.askExpectedDay(month.getMonthValue());
        return input.readDate(month);
    }

    private OrderMenu readOrder() {
        output.askOrder();
        return input.readOrders();
    }
}
