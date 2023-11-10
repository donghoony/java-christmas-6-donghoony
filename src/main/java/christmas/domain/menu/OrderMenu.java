package christmas.domain.menu;

import java.util.List;

public class OrderMenu {
    private final int MAX_ORDER_COUNT = 20;

    private final List<MenuAmount> orderedMenu;

    public OrderMenu(List<MenuAmount> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public int getCategoryCount(Category category) {
        return orderedMenu.stream()
                .filter(menuAmount -> menuAmount.menu().isCategorySameAs(category))
                .mapToInt(MenuAmount::amount)
                .sum();
    }

    public long getTotalPrice() {
        return orderedMenu.stream().mapToLong(MenuAmount::getTotalPrice).sum();
    }
}
