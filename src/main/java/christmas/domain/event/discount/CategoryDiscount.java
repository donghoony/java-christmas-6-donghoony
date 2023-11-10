package christmas.domain.event.discount;

import christmas.domain.Money;
import christmas.domain.menu.Category;
import christmas.domain.menu.OrderMenu;

public class CategoryDiscount implements Discount {
    private final Category category;
    private final Money discountAmount;

    public CategoryDiscount(Category category, Money discountAmount) {
        this.category = category;
        this.discountAmount = discountAmount;
    }

    @Override
    public Money apply(OrderMenu orderMenu) {
        long sameCategoryCount = orderMenu.getCategoryCount(category);
        return discountAmount.multiply(sameCategoryCount);
    }
}
