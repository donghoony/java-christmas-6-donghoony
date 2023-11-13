package christmas.domain.event.discount;

import christmas.domain.Beneficial;
import christmas.domain.Money;
import christmas.domain.event.Event;
import christmas.domain.menu.Category;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;

public class CategoryDiscount implements Event {
    private final Category category;
    private final Money discountAmount;

    public CategoryDiscount(Category category, Money discountAmount) {
        this.category = category;
        this.discountAmount = discountAmount;
    }

    @Override
    public Beneficial apply(LocalDate date, OrderMenu orderMenu) {
        long sameCategoryCount = orderMenu.getCategoryCount(category);
        return discountAmount.multiply(sameCategoryCount);
    }
}
