package christmas.domain.event;

import christmas.domain.Money;

public class EventBenefitDetail {
    private final String eventName;
    private final Money discountAmount;

    public EventBenefitDetail(String eventName, Money discountAmount) {
        this.eventName = eventName;
        this.discountAmount = discountAmount;
    }

    public Money getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public String toString() {
        return eventName + ": " + discountAmount;
    }
}
