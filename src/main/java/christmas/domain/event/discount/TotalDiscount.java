package christmas.domain.event.discount;

import christmas.domain.Beneficial;
import christmas.domain.Money;
import christmas.domain.event.Event;
import christmas.domain.menu.OrderMenu;

public class TotalDiscount implements Event {
    private final Money discountAmount;

    public TotalDiscount(Money discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public Beneficial apply(OrderMenu orderMenu) {
        return discountAmount;
    }
}
