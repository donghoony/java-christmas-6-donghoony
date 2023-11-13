package christmas.io;

import christmas.domain.GiveawayProducts;
import christmas.domain.Money;
import christmas.domain.badge.Badge;
import christmas.domain.event.TotalEventBenefitDetails;
import christmas.domain.menu.OrderMenu;

public class PlannerConsoleOutput implements PlannerOutput {
    @Override
    public void askExpectedDay(int month) {
        System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n", month);
        System.out.printf("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n", month);
    }

    @Override
    public void askOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    @Override
    public void printAbstractIntroduction() {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    @Override
    public void printOrderMenu(OrderMenu orderMenu) {
        System.out.println("<주문 메뉴>");
        System.out.println(orderMenu);
        System.out.println();
    }

    @Override
    public void printTotalPriceBeforeDiscount(Money amount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(amount);
        System.out.println();
    }

    @Override
    public void printBenefitExceptMoney(GiveawayProducts products) {
        System.out.println("<증정 메뉴>");
        System.out.println(products);
        System.out.println();
    }

    @Override
    public void printTotalBenefits(TotalEventBenefitDetails benefitDetails) {
        System.out.println("<혜택 내역>");
        System.out.println(benefitDetails);
        System.out.println();
    }

    @Override
    public void printTotalBenefitAmount(Money amount) {
        System.out.println("<총혜택 금액>");
        System.out.println(amount);
        System.out.println();
    }

    @Override
    public void printTotalPriceAfterDiscount(Money amount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(amount);
        System.out.println();
    }

    @Override
    public void printEventBadge(int month, Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
        System.out.println();
    }
}
