package christmas.domain.event.discount;

import christmas.domain.menu.Category;
import christmas.domain.menu.OrderMenu;

public class CategoryDiscount implements Discount {
    private final Category category;
    private final long discountAmount;

    public CategoryDiscount(Category category, long discountAmount) {
        this.category = category;
        this.discountAmount = discountAmount;
    }

    @Override
    public long apply(OrderMenu orderMenu) {
        long sameCategoryCount = orderMenu.getCategoryCount(category);
        return sameCategoryCount * discountAmount;
    }
}
