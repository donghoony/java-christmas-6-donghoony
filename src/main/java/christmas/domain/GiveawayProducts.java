package christmas.domain;

import java.util.List;
import java.util.StringJoiner;

public class GiveawayProducts {
    private final List<String> productNames;

    public GiveawayProducts(List<String> productNames) {
        this.productNames = productNames;
    }

    @Override
    public String toString() {
        if (productNames.isEmpty()) {
            return "없음";
        }

        StringJoiner stringJoiner = new StringJoiner("\n");
        productNames.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }
}
