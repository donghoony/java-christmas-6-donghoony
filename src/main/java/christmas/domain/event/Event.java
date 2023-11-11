package christmas.domain.event;

import christmas.domain.menu.OrderMenu;
import java.time.LocalDate;

public interface Event {
    EventBenefitDetail getBenefitDetail(OrderMenu orderMenu);

    boolean isAvailableEvent(LocalDate date, OrderMenu orderMenu);
}
