package christmas.domain.event.giveaway;

import christmas.domain.Money;

public record GiveawayProduct(
        String name,
        Money price
) {
}
