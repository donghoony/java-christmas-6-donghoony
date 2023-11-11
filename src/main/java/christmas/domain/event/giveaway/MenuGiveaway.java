package christmas.domain.event.giveaway;

import christmas.domain.menu.Menu;
import christmas.domain.menu.OrderMenu;

public class MenuGiveaway implements Giveaway {
    private final Menu giveawayMenu;

    public MenuGiveaway(Menu giveawayMenu) {
        this.giveawayMenu = giveawayMenu;
    }

    @Override
    public GiveawayProduct getGiveawayProduct(OrderMenu orderMenu) {
        return new GiveawayProduct(giveawayMenu.name(), giveawayMenu.getPrice());
    }
}
