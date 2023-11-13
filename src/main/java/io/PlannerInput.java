package io;

import christmas.domain.menu.MenuAmount;
import java.time.YearMonth;
import java.util.List;

public interface PlannerInput {
    int readDate(YearMonth month);

    List<MenuAmount> readOrders();
}
