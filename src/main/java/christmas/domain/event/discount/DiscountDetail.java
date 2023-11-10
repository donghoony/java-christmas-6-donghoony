package christmas.domain.event.discount;

import christmas.domain.Money;

public class DiscountDetail {
    private final String discountName;
    private final Money discountAmount;

    public DiscountDetail(String discountName, Money discountAmount) {
        this.discountName = discountName;
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return discountName + ": " + discountAmount;
    }
}
