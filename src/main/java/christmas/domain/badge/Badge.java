package christmas.domain.badge;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000L),
    TREE("트리", 10_000L),
    STAR("별", 5_000L),
    NONE("없음", 0L);

    private final String name;
    private final long minimumBenefitAmount;

    Badge(String name, long minimumBenefitAmount) {
        this.name = name;
        this.minimumBenefitAmount = minimumBenefitAmount;
    }

    public static Badge getEligibleBadgeByBenefit(long benefitAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.minimumBenefitAmount <= benefitAmount)
                .findFirst()
                .orElse(NONE);
    }

}
