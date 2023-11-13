package christmas.service;

import christmas.domain.Money;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.event.PlannerEvent;
import christmas.domain.event.TotalEventBenefitDetails;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.List;

public class EventService {
    private final List<PlannerEvent> plannerEvents;

    public EventService(List<PlannerEvent> plannerEvents) {
        this.plannerEvents = plannerEvents;
    }

    public TotalEventBenefitDetails apply(LocalDate currentDate, OrderMenu orderMenu) {
        List<EventBenefitDetail> benefitDetails = plannerEvents.stream()
                .filter(plannerEvent -> plannerEvent.isEligible(currentDate, orderMenu))
                .map(plannerEvent -> plannerEvent.getBenefitDetail(currentDate, orderMenu))
                .filter(benefitDetail -> benefitDetail.getPrice().compareTo(Money.of(0L)) > 0)
                .toList();

        return new TotalEventBenefitDetails(benefitDetails);
    }
}
