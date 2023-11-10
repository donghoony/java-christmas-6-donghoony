package christmas.domain.event.discount;

import christmas.domain.Money;
import christmas.domain.menu.OrderMenu;

public class TotalDiscount implements Discount {
    private final Money discountAmount;

    public TotalDiscount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public Money apply(OrderMenu orderMenu) {
        return discountAmount;
    }
}
