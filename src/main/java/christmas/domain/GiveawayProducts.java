package christmas.domain;

import java.util.List;

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

        return String.join("\n", productNames);
    }
}
