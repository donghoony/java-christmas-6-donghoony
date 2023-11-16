package christmas.domain.event;

import christmas.domain.Beneficial;
import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;

public interface Event {
    Beneficial apply(LocalDate date, OrderMenu orderMenu);
}
