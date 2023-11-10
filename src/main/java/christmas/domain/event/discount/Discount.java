package christmas.domain.event.discount;

import christmas.domain.Money;
import christmas.domain.menu.OrderMenu;

public interface Discount {
    Money apply(OrderMenu orderMenu);
}
