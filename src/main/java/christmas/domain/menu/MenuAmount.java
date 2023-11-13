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
        StringBuilder sb = new StringBuilder();
        sb.append(menu.name()).append(" ").append(amount);
        return sb.toString();
    }
}
