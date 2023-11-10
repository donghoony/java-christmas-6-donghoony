package christmas.domain.menu;

import christmas.domain.Money;

public record MenuAmount(
        Menu menu,
        int amount
) {
    public Money getTotalPrice() {
        return menu.getPrice().multiply(amount);
    }
}
