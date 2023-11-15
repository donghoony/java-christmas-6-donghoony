package christmas.domain.event;

import christmas.domain.GiveawayProducts;
import christmas.domain.Money;
import java.util.List;

public class TotalEventBenefitDetails {
    private final List<EventBenefitDetail> benefitDetails;

    public TotalEventBenefitDetails(List<EventBenefitDetail> benefitDetails) {
        this.benefitDetails = benefitDetails;
    }

    public Money getTotalBenefitAmount() {
        return benefitDetails.stream()
                .map(EventBenefitDetail::getPrice)
                .reduce(Money.of(0L), Money::add);
    }

    public Money getTotalBenefitAmountWithoutGiveawayProducts() {
        return benefitDetails.stream()
                .filter(benefitDetail -> !benefitDetail.isGiveawayProduct())
                .map(EventBenefitDetail::getPrice)
                .reduce(Money.of(0L), Money::add);
    }

    public GiveawayProducts getGiveawayProducts() {
        List<String> productNames = benefitDetails.stream()
                .filter(EventBenefitDetail::isGiveawayProduct)
                .map(EventBenefitDetail::getBenefitAsString)
                .toList();

        return new GiveawayProducts(productNames);
    }

    @Override
    public String toString() {
        if (benefitDetails.isEmpty()) {
            return "없음";
        }

        List<String> details = benefitDetails.stream()
                .map(EventBenefitDetail::toString)
                .toList();
        return String.join("\n", details);
    }
}
