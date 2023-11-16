package christmas.domain.menu;

import christmas.domain.Beneficial;
import christmas.domain.Money;

public record MenuAmount(
        Menu menu,
        int amount
) implements Beneficial {

    @Override
    public Money getPrice() {
        return menu.getPrice().multiply(amount);
    }

    @Override
    public String toString() {
        return menu.toString() + " " + amount + "개";
    }
}
