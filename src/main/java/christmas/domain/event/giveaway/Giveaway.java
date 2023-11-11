package christmas.domain.event.giveaway;

import christmas.domain.menu.OrderMenu;

public interface Giveaway {
    GiveawayProduct getGiveawayProduct(OrderMenu orderMenu);

    default boolean isEligible(OrderMenu orderMenu) {
        return true;
    }
}
