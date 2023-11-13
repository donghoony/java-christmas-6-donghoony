package christmas.domain.menu;

import christmas.domain.Money;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP(Category.APPETIZER, "양송이수프", 6_000L),
    TAPAS(Category.APPETIZER, "타파스", 5_500L),
    CAESAR_SALAD(Category.APPETIZER, "시저샐러드", 8_000L),

    T_BONE_STEAK(Category.MAIN_DISH, "티본스테이크", 55_000L),
    BARBEQUE_RIB(Category.MAIN_DISH, "바비큐립", 54_000L),
    SEAFOOD_PASTA(Category.MAIN_DISH, "해산물파스타", 35_000L),
    CHRISTMAS_PASTA(Category.MAIN_DISH, "크리스마스파스타", 25_000L),

    CHOCOLATE_CAKE(Category.DESSERT, "초코케이크", 15_000L),
    ICE_CREAM(Category.DESSERT, "아이스크림", 5_000L),

    ZERO_COKE(Category.BEVERAGE, "제로콜라", 3_000L),
    RED_WINE(Category.BEVERAGE, "레드와인", 60_000L),
    CHAMPAGNE(Category.BEVERAGE, "샴페인", 25_000L),

    NONE(null, "없음", 0L),
    ;

    private final Category category;
    private final String name;
    private final Money price;

    Menu(Category category, String name, long price) {
        this.category = category;
        this.name = name;
        this.price = Money.of(price);
    }

    public Money getPrice() {
        return price;
    }

    public boolean isCategorySameAs(Category category) {
        return this.category == category;
    }

    public static Menu from(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElse(NONE);
    }
}
