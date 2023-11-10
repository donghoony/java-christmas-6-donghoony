package christmas.domain;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Objects;

public class Money {
    private final String CURRENCY = "원";
    private final BigInteger amount;

    private Money(BigInteger amount) {
        this.amount = amount;
    }

    private Money(long amount) {
        this(BigInteger.valueOf(amount));
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    @Override
    public String toString() {
        String formattedAmount = NumberFormat.getInstance().format(amount);
        return formattedAmount + CURRENCY;
    }

    public Money multiply(long value) {
        BigInteger bigValue = BigInteger.valueOf(value);
        BigInteger newAmount = this.amount.multiply(bigValue);
        return new Money(newAmount);
    }

    public Money add(Money money) {
        BigInteger newAmount = this.amount.add(money.amount);
        return new Money(newAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CURRENCY, amount);
    }
}