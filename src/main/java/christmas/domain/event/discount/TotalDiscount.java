package christmas.domain.event.discount;

import christmas.domain.menu.OrderMenu;

public class TotalDiscount implements Discount {
    private final long discountAmount;

    public TotalDiscount(long discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public long apply(OrderMenu orderMenu) {
        return discountAmount;
    }
}
