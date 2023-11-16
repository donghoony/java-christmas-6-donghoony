package christmas.domain;

public interface Beneficial {
    Money getPrice();

    @Override
    String toString();

    default boolean isMoney() {
        return this instanceof Money;
    }
}
