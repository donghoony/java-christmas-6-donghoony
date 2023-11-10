package christmas.domain.event.giveaway;

import christmas.domain.Money;
import java.time.LocalDate;

public interface Giveaway {
    Money getBenefitAmount();
}
