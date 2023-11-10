package christmas.domain.event.eventdate;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecificEventDateTest {
    @Test
    @DisplayName("이벤트 날짜가 정해졌을 때, 특정 날짜가 이벤트 날짜인지 올바르게 판단한다.")
    public void specificDateTest() {
        // given
        List<LocalDate> eventDates = List.of(
                LocalDate.of(2023, 11, 10),
                LocalDate.of(2023, 11, 11),
                LocalDate.of(2023, 11, 13)
        );
        // when
        SpecificEventDate specificEventDate = new SpecificEventDate(eventDates);
        // then
        assertThat(specificEventDate.isAvailableEvent(LocalDate.of(2023, 11, 9))).isFalse();
        assertThat(specificEventDate.isAvailableEvent(LocalDate.of(2023, 11, 10))).isTrue();
        assertThat(specificEventDate.isAvailableEvent(LocalDate.of(2023, 11, 13))).isTrue();
        assertThat(specificEventDate.isAvailableEvent(LocalDate.of(2023, 12, 10))).isFalse();
    }
}