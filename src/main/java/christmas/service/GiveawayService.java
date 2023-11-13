package christmas.service;

import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.giveaway.GiveawayProduct;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;
import java.util.List;

public class GiveawayService {
    private final LocalDate currentDate;
    private final List<GiveawayEvent> giveawayEvents;

    public GiveawayService(LocalDate currentDate, List<GiveawayEvent> giveawayEvents) {
        this.currentDate = currentDate;
        this.giveawayEvents = giveawayEvents;
    }

    public List<GiveawayProduct> applyGiveaway(OrderMenu orderMenu) {
        return giveawayEvents.stream()
                .filter(giveawayEvent -> giveawayEvent.isAvailableEvent(currentDate, orderMenu))
                .map(giveawayEvent -> giveawayEvent.getGiveawayProduct(orderMenu))
                .toList();
    }
}
