package christmas.domain.event.eventdate;

import java.time.LocalDate;
import java.util.List;

public class SpecificEventDate implements EventDate {
    private final List<LocalDate> specificDates;

    public SpecificEventDate(List<LocalDate> specificDates) {
        this.specificDates = specificDates;
    }

    @Override
    public boolean isAvailableEvent(LocalDate date) {
        return specificDates.contains(date);
    }
}
