package christmas.domain.event;

import christmas.domain.menu.OrderMenu;

public interface Event {
    EventBenefitDetail getBenefitDetail(OrderMenu orderMenu);
}
