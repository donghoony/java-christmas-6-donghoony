package christmas.domain.event.eventdate;

import java.time.LocalDate;

public interface EventDate {
    boolean isAvailableEvent(LocalDate date);
}
