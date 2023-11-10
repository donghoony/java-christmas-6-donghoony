package christmas.domain.event.giveaway;

import christmas.domain.Money;
import christmas.domain.menu.MenuAmount;

public class MenuGiveaway implements Giveaway {
    private final MenuAmount giveawayMenu;

    public MenuGiveaway(MenuAmount giveawayMenu) {
        this.giveawayMenu = giveawayMenu;
    }

    @Override
    public Money getBenefitAmount() {
        return giveawayMenu.getTotalPrice();
    }
}
