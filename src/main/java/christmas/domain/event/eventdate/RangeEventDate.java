package christmas.domain.event.eventdate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class RangeEventDate implements EventDate {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<DayOfWeek> specificDayOfWeeks;

    public RangeEventDate(LocalDate startDate, LocalDate endDate, List<DayOfWeek> dayOfWeeks) {
        this.startDate = startDate;
        this.endDate = endDate;
        specificDayOfWeeks = dayOfWeeks;
    }

    public RangeEventDate(LocalDate startDate, LocalDate endDate) {
        this(startDate, endDate, List.of(DayOfWeek.values()));
    }

    @Override
    public boolean isAvailableEvent(LocalDate date) {
        return inRangeDate(date) && containsDay(date);
    }

    private boolean inRangeDate(LocalDate date) {
        return date.isAfter(startDate.minusDays(1)) &&
                date.isBefore(endDate.plusDays(1));
    }

    private boolean containsDay(LocalDate date) {
        return specificDayOfWeeks.contains(date.getDayOfWeek());
    }
}
