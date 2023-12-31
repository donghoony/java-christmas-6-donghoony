package christmas.domain.badge;

import christmas.domain.Money;
import java.util.Arrays;
import java.util.Comparator;

public enum Badge implements Comparable<Badge> {
    NONE("없음", 0L),
    STAR("별", 5_000L),
    TREE("트리", 10_000L),
    SANTA("산타", 20_000L);

    private final String name;
    private final Money minimumBenefitAmount;

    Badge(String name, long minimumBenefitAmount) {
        this.name = name;
        this.minimumBenefitAmount = Money.of(minimumBenefitAmount);
    }

    public static Badge getEligibleBadgeByBenefit(Money benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumBenefitAmount.compareTo(benefitAmount) <= 0)
                .max(Comparator.comparing(Badge::getMinimumBenefitAmount))
                .orElse(NONE);
    }

    public Money getMinimumBenefitAmount() {
        return minimumBenefitAmount;
    }

    @Override
    public String toString() {
        return name;
    }
}
