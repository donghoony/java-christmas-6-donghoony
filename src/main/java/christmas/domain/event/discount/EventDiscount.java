package christmas.domain.event.discount;

import christmas.domain.event.eventdate.EventDate;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;

public class EventDiscount {
    private final String eventName;
    private final Discount discount;
    private final EventDate eventDate;

    public EventDiscount(String eventName, Discount discount, EventDate eventDate) {
        this.eventName = eventName;
        this.discount = discount;
        this.eventDate = eventDate;
    }

    public DiscountDetail apply(OrderMenu orderMenu) {
        long discountAmount = discount.apply(orderMenu);
        return new DiscountDetail(this.eventName, discountAmount);
    }

    public boolean isAvailableEvent(LocalDate date) {
        return eventDate.isAvailableEvent(date);
    }
}
