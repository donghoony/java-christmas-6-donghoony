package christmas.io;

import christmas.domain.Money;
import christmas.domain.badge.Badge;
import christmas.domain.event.EventBenefitDetail;
import christmas.domain.menu.OrderMenu;
import java.util.List;

public interface PlannerOutput {
    void askExpectedDay(int month);

    void askOrder();

    void printAbstractIntroduction();

    void printOrderMenu(OrderMenu orderMenu);

    void printTotalPriceBeforeDiscount(Money amount);

    void printBenefitExceptMoney(List<String> benefits);

    void printTotalBenefits(List<EventBenefitDetail> benefitDetails);

    void printTotalBenefitAmount(Money amount);

    void printTotalPriceAfterDiscount(Money amount);

    void printEventBadge(int month, Badge badge);
}
