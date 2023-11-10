package christmas.service;

import christmas.domain.event.DiscountEvent;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.List;

public class DiscountService {
    private final LocalDate currentDate;
    private final List<DiscountEvent> discountEvents;

    public DiscountService(LocalDate currentDate, List<DiscountEvent> discountEvents) {
        this.currentDate = currentDate;
        this.discountEvents = discountEvents;
    }

    public List<EventBenefitDetail> applyDiscounts(OrderMenu orderMenu) {
        return discountEvents.stream()
                .filter(discountEvent -> discountEvent.isAvailableEvent(currentDate))
                .map(discountEvent -> discountEvent.getBenefitDetail(orderMenu))
                .toList();
    }
}
