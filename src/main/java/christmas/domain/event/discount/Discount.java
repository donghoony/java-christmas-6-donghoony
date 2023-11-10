package christmas.domain.event.discount;

import christmas.domain.Money;
import christmas.domain.event.Event;
import christmas.domain.menu.OrderMenu;

public interface Discount extends Event {
    Money apply(OrderMenu orderMenu);
}
