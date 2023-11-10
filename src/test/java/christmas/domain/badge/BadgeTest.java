package christmas.domain.badge;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Money;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @ParameterizedTest
    @ValueSource(longs = {0L, 4_999L, 5_000L, 9_999L, 10_000L, 19_999L, 20_000L, 100_000L})
    @DisplayName("혜택 가격에 따른 올바른 뱃지를 리턴한다.")
    public void createBadgeTest(long benefitAmount) {
        // given
        Map<Long, Badge> expectedBadge = Map.of(
                0L, Badge.NONE,
                4_999L, Badge.NONE,
                5_000L, Badge.STAR,
                9_999L, Badge.STAR,
                10_000L, Badge.TREE,
                19_999L, Badge.TREE,
                20_000L, Badge.SANTA,
                100_000L, Badge.SANTA
        );
        // when
        Badge badge = Badge.getEligibleBadgeByBenefit(Money.of(benefitAmount));
        // then
        assertThat(badge).isEqualTo(expectedBadge.get(benefitAmount));
    }
}