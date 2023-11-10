package christmas.domain.event;

import christmas.domain.Money;
import christmas.domain.event.eventdate.EventDate;
import christmas.domain.event.giveaway.Giveaway;
import christmas.domain.menu.OrderMenu;

public class EventGiveaway implements Event {
    private final String eventName;
    private final Giveaway giveaway;
    private final EventDate eventDate;

    public EventGiveaway(String eventName, Giveaway giveaway, EventDate eventDate) {
        this.eventName = eventName;
        this.giveaway = giveaway;
        this.eventDate = eventDate;
    }

    @Override
    public EventBenefitDetail getBenefitDetail(OrderMenu orderMenu) {
        Money benefitAmount = giveaway.getBenefitAmount();
        return new EventBenefitDetail(eventName, benefitAmount);
    }
}
