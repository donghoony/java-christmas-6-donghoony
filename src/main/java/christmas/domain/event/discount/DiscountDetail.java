package christmas.domain.event.discount;

public class DiscountDetail {
    private final String discountName;
    private final long discountAmount;

    public DiscountDetail(String discountName, long discountAmount) {
        this.discountName = discountName;
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return discountName + ": " + String.format("%,d", discountAmount) + "원";
    }
}
