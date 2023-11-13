package christmas.io;

import christmas.domain.menu.OrderMenu;
import java.time.YearMonth;

public interface PlannerInput {
    int readDate(YearMonth month);

    OrderMenu readOrders();
}
