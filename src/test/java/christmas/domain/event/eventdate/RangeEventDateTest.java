package christmas.domain.event.eventdate;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RangeEventDateTest {
    @Test
    @DisplayName("이벤트 날짜 범위가 주어질 때, 특정 날짜가 이벤트 날짜인지 확인한다.")
    public void rangeDateTest() {
        // given
        LocalDate startAt = LocalDate.of(2023, 11, 1);
        LocalDate endAt = LocalDate.of(2023, 11, 3);
        // when
        RangeEventDate rangeEventDate = new RangeEventDate(startAt, endAt);
        // then
        assertThat(rangeEventDate.isAvailableEvent(startAt.minusDays(1))).isFalse();
        assertThat(rangeEventDate.isAvailableEvent(endAt.plusDays(1))).isFalse();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 11, 2))).isTrue();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2024, 11, 2))).isFalse();
    }

    @Test
    @DisplayName("이벤트 날짜 범위와 요일이 주어질 때, 특정 날짜가 이벤트 날짜인지 확인한다.")
    public void specificWeekOfDayTest() {
        // given
        LocalDate startAt = LocalDate.of(2023, 12, 1);
        LocalDate endAt = LocalDate.of(2023, 12, 31);
        List<DayOfWeek> dayOfWeeks = List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY);
        // when
        RangeEventDate rangeEventDate = new RangeEventDate(startAt, endAt, dayOfWeeks);

        // Monday, Tuesday
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 4))).isTrue();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 5))).isTrue();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 25))).isTrue();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 26))).isTrue();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 11, 27))).isFalse();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2024, 1, 1))).isFalse();

        // Other days
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 6))).isFalse();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 7))).isFalse();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 8))).isFalse();
        assertThat(rangeEventDate.isAvailableEvent(LocalDate.of(2023, 12, 9))).isFalse();
    }
}
