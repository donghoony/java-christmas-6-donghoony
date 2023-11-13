package christmas.service;

import christmas.domain.event.PlannerEvent;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.List;

public class EventService {
    private final LocalDate currentDate;
    private final List<PlannerEvent> plannerEvents;

    public EventService(LocalDate currentDate, List<PlannerEvent> plannerEvents) {
        this.currentDate = currentDate;
        this.plannerEvents = plannerEvents;
    }

    public List<EventBenefitDetail> apply(OrderMenu orderMenu) {
        return plannerEvents.stream()
                .filter(plannerEvent -> plannerEvent.isEligible(currentDate, orderMenu))
                .map(plannerEvent -> plannerEvent.getBenefitDetail(orderMenu))
                .toList();
    }
}
