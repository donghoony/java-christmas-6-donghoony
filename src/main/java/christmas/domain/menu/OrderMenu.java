package christmas.domain.menu;

import christmas.domain.Money;
import christmas.exception.ExceptionMessage;
import christmas.exception.PlannerException;
import java.util.List;

public class OrderMenu {
    private final int MAX_ORDER_COUNT = 20;

    private final List<MenuAmount> orderedMenu;

    public OrderMenu(List<MenuAmount> orderedMenu) {
        validateOrderMenu(orderedMenu);
        this.orderedMenu = orderedMenu;
    }

    public int getCategoryCount(Category category) {
        return orderedMenu.stream()
                .filter(menuAmount -> menuAmount.menu().isCategorySameAs(category))
                .mapToInt(MenuAmount::amount)
                .sum();
    }

    public Money getTotalPrice() {
        return orderedMenu.stream()
                .map(MenuAmount::getTotalPrice)
                .reduce(Money.of(0L), Money::add);
    }

    private void validateOrderMenu(List<MenuAmount> orderedMenu) {
        validateDishesIncluded(orderedMenu);
        validateMenuAmount(orderedMenu);
        validateDistinctMenu(orderedMenu);
    }

    private void validateDishesIncluded(List<MenuAmount> orderedMenu) {
        orderedMenu.stream()
                .map(MenuAmount::menu)
                .filter(menu -> !menu.isCategorySameAs(Category.BEVERAGE))
                .findAny()
                .orElseThrow(() -> new PlannerException(ExceptionMessage.INVALID_ORDER));
    }

    private void validateMenuAmount(List<MenuAmount> orderedMenu) {
        int totalAmount = orderedMenu.stream()
                .mapToInt(MenuAmount::amount)
                .sum();

        if (MAX_ORDER_COUNT < totalAmount) {
            throw new PlannerException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private void validateDistinctMenu(List<MenuAmount> orderedMenu) {
        int distinctMenuCount = (int) orderedMenu.stream()
                .map(MenuAmount::menu)
                .distinct()
                .count();

        if (distinctMenuCount != orderedMenu.size()) {
            throw new PlannerException(ExceptionMessage.INVALID_ORDER);
        }
    }

}
