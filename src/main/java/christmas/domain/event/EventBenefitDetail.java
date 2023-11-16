package christmas.domain.event;

import christmas.domain.Beneficial;
import christmas.domain.Money;

public class EventBenefitDetail {
    private static final String NEGATIVE_SIGN = "-";

    private final String eventName;
    private final Beneficial benefit;

    public EventBenefitDetail(String eventName, Beneficial benefit) {
        this.eventName = eventName;
        this.benefit = benefit;
    }

    public boolean isGiveawayProduct() {
        return !benefit.isMoney();
    }

    public String getBenefitAsString() {
        return benefit.toString();
    }

    public Money getPrice() {
        return benefit.getPrice();
    }

    @Override
    public String toString() {
        return eventName + ": " + NEGATIVE_SIGN + benefit.getPrice();
    }
}
