package christmas.service;

import christmas.domain.Money;
import christmas.domain.badge.Badge;

public class BadgeService {

    public Badge getBadgeByTotalBenefit(Money totalBenefit) {
        return Badge.getEligibleBadgeByBenefit(totalBenefit);
    }
}
