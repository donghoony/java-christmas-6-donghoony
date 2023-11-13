package christmas.service;

import christmas.domain.event.EventBenefitDetail;
import christmas.domain.event.PlannerEvent;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.List;

public class EventService {
    private final List<PlannerEvent> plannerEvents;

    public EventService(List<PlannerEvent> plannerEvents) {
        this.plannerEvents = plannerEvents;
    }

    public List<String> getBenefitDetailsExceptMoney(LocalDate currentDate, OrderMenu orderMenu) {
        return apply(currentDate, orderMenu)
                .stream()
                .filter(EventBenefitDetail::isGiveawayProduct)
                .map(EventBenefitDetail::getBenefitAsString)
                .toList();
    }

    public List<EventBenefitDetail> apply(LocalDate currentDate, OrderMenu orderMenu) {
        return plannerEvents.stream()
                .filter(plannerEvent -> plannerEvent.isEligible(currentDate, orderMenu))
                .map(plannerEvent -> plannerEvent.getBenefitDetail(orderMenu))
                .toList();
    }
}
