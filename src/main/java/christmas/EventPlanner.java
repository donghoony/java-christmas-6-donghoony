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

        TotalEventBenefitDetails benefitDetails = eventService.apply(today, orderMenu);

        output.printAbstractIntroduction();

        output.printOrderMenu(orderMenu);
        output.printTotalPriceBeforeDiscount(totalPrice);

        GiveawayProducts giveawayProducts = benefitDetails.getGiveawayProducts();
        output.printBenefitExceptMoney(giveawayProducts);

        output.printTotalBenefits(benefitDetails);

        Money totalBenefitPrice = benefitDetails.getTotalBenefitAmount();
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
