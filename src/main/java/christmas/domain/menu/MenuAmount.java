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
        sb.append(menu.toString()).append(" ").append(amount).append("개");
        return sb.toString();
    }
}
