package christmas.domain.event.giveaway;

import christmas.domain.Beneficial;
import christmas.domain.event.Event;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;

public class MenuGiveaway implements Event {
    private final Beneficial giveawayProduct;

    public MenuGiveaway(Beneficial giveawayProduct) {
        this.giveawayProduct = giveawayProduct;
    }

    @Override
    public Beneficial apply(LocalDate date, OrderMenu orderMenu) {
        return giveawayProduct;
    }
}
