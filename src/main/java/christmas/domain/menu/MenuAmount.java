package christmas.domain.menu;

public record MenuAmount(
        Menu menu,
        int amount
) {
    public long getTotalPrice() {
        return menu.getPrice() * amount;
    }
}
