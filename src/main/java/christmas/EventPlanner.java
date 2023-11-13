package christmas;

import christmas.domain.Money;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.menu.OrderMenu;
import christmas.service.BadgeService;
import christmas.service.EventService;
import io.PlannerInput;
import io.PlannerOutput;
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

        List<EventBenefitDetail> eventBenefitDetails = eventService.apply(orderMenu);

        output.printAbstractIntroduction();

        output.printOrderMenu(orderMenu);
        output.printTotalPriceBeforeDiscount(totalPrice);



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
