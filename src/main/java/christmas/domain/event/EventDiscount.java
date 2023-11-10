package christmas.domain.event;

import christmas.domain.Money;
import christmas.domain.event.discount.Discount;
import christmas.domain.event.eventdate.EventDate;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;

public class EventDiscount implements Event {
    private final String eventName;
    private final Discount discount;
    private final EventDate eventDate;

    public EventDiscount(String eventName, Discount discount, EventDate eventDate) {
        this.eventName = eventName;
        this.discount = discount;
        this.eventDate = eventDate;
    }

    @Override
    public EventBenefitDetail getBenefitDetail(OrderMenu orderMenu) {
        Money discountAmount = discount.apply(orderMenu);
        return new EventBenefitDetail(this.eventName, discountAmount);
    }

    public boolean isAvailableEvent(LocalDate date) {
        return eventDate.isAvailableEvent(date);
    }
}
