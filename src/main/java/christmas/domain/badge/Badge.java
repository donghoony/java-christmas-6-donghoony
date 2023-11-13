package christmas.domain.badge;

import christmas.domain.Money;
import java.util.Arrays;

public enum Badge {
    SANTA("산타", -20_000L),
    TREE("트리", -10_000L),
    STAR("별", -5_000L),
    NONE("없음", 0L);

    private final String name;
    private final Money minimumBenefitAmount;

    Badge(String name, long minimumBenefitAmount) {
        this.name = name;
        this.minimumBenefitAmount = Money.of(minimumBenefitAmount);
    }

    public static Badge getEligibleBadgeByBenefit(Money benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumBenefitAmount.compareTo(benefitAmount) >= 0)
                .findFirst()
                .orElse(NONE);
    }

    @Override
    public String toString() {
        return name;
    }
}
