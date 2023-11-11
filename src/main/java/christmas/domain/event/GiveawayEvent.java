package christmas.domain.event;

import christmas.domain.event.eventdate.EventDate;
import christmas.domain.event.giveaway.Giveaway;
import christmas.domain.event.giveaway.GiveawayProduct;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;

public class GiveawayEvent implements Event {
    private final String eventName;
    private final Giveaway giveaway;
    private final EventDate eventDate;

    public GiveawayEvent(String eventName, Giveaway giveaway, EventDate eventDate) {
        this.eventName = eventName;
        this.giveaway = giveaway;
        this.eventDate = eventDate;
    }

    @Override
    public EventBenefitDetail getBenefitDetail(OrderMenu orderMenu) {
        GiveawayProduct giveawayProduct = giveaway.getGiveawayProduct(orderMenu);
        return new EventBenefitDetail(eventName, giveawayProduct.price().multiply(-1L));
    }

    @Override
    public boolean isAvailableEvent(LocalDate date, OrderMenu orderMenu) {
        return eventDate.isAvailableEvent(date) && giveaway.isEligible(orderMenu);
    }
}
