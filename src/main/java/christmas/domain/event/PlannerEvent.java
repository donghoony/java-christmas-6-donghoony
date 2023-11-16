package christmas.domain.event;

import christmas.domain.Beneficial;
import christmas.domain.event.eventdate.EventDate;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.function.Predicate;

public class PlannerEvent {
    private final String eventName;
    private final Event event;
    private final EventDate eventDate;
    private final Predicate<OrderMenu> eligibility;

    public PlannerEvent(String eventName, Event event, EventDate eventDate, Predicate<OrderMenu> eligibility) {
        this.eventName = eventName;
        this.event = event;
        this.eventDate = eventDate;
        this.eligibility = eligibility;
    }

    public EventBenefitDetail getBenefitDetail(LocalDate date, OrderMenu orderMenu) {
        Beneficial benefit = event.apply(date, orderMenu);
        return new EventBenefitDetail(this.eventName, benefit);
    }

    public boolean isEligible(LocalDate date, OrderMenu orderMenu) {
        return eventDate.isAvailableEvent(date) && eligibility.test(orderMenu);
    }
}
