package christmas;

import christmas.domain.Money;
import christmas.io.PlannerConsoleInput;
import christmas.io.PlannerConsoleOutput;
import christmas.io.PlannerInput;
import christmas.io.PlannerOutput;
import christmas.service.BadgeService;
import christmas.service.EventService;
import java.time.YearMonth;

public class Application {
    public static void main(String[] args) {
        YearMonth month = YearMonth.of(2023, 12);

        PlannerConfig plannerConfig = new PlannerConfig(month, Money.of(10_000L));
        EventService eventService = new EventService(plannerConfig.getEvents());
        BadgeService badgeService = new BadgeService();
        PlannerInput input = new PlannerConsoleInput();
        PlannerOutput output = new PlannerConsoleOutput();

        EventPlanner planner = new EventPlanner(eventService, badgeService, input, output, month);
        planner.planEvent();
    }
}
