package christmas.domain.event;

import christmas.domain.Beneficial;
import christmas.domain.menu.OrderMenu;

public interface Event {
    Beneficial apply(OrderMenu orderMenu);
}
