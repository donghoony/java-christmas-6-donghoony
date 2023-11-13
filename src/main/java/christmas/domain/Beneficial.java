package christmas.domain;

public interface Beneficial {
    Money getPrice();

    @Override
    String toString();
}
