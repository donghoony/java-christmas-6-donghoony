package christmas.io;

import christmas.domain.GiveawayProducts;
import christmas.domain.Money;
import christmas.domain.badge.Badge;
import christmas.domain.event.TotalEventBenefitDetails;
import christmas.domain.menu.OrderMenu;

public interface PlannerOutput {
    void askExpectedDay(int month);

    void askOrder();

    void printAbstractIntroduction(int month, int day);

    void printOrderMenu(OrderMenu orderMenu);

    void printTotalPriceBeforeDiscount(Money amount);

    void printGiveawayProducts(GiveawayProducts products);

    void printTotalBenefits(TotalEventBenefitDetails benefitDetails);

    void printTotalBenefitAmount(Money amount);

    void printTotalPriceAfterDiscount(Money amount);

    void printEventBadge(int month, Badge badge);
}
