package christmas;

import christmas.exception.PlannerException;
import java.util.function.Supplier;

public interface ExceptionLoopClient {
    default <T> T repeatUntilValid(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (PlannerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
