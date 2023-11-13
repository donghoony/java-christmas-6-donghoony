package io;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAmount;
import christmas.exception.ExceptionMessage;
import christmas.exception.PlannerException;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

public class PlannerConsoleInput implements PlannerInput {
    private final int MIN_AMOUNT_ORDER = 1;

    @Override
    public int readDate(YearMonth month) {
        String input = Console.readLine();
        validateDate(input);

        int day = Integer.parseInt(input);
        validateDateInMonth(month, day);
        return day;
    }

    @Override
    public List<MenuAmount> readOrders() {
        final String DELIMITER = ",";

        String input = Console.readLine();
        String[] splitInput = input.split(DELIMITER);

        return Arrays.stream(splitInput)
                .map(this::parseMenuAmount)
                .toList();
    }

    private MenuAmount parseMenuAmount(String orderInput) {
        final String DELIMITER = "-";
        String[] orderAmountInput = orderInput.split(DELIMITER);

        validateOrderAmountSyntax(orderAmountInput);

        Menu menu = parseMenu(orderAmountInput[0]);
        int amount = parseAmount(orderAmountInput[1]);

        return new MenuAmount(menu, amount);
    }

    private void validateOrderAmountSyntax(String[] orderAmountInput) {
        if (orderAmountInput.length != 2) {
            throw new PlannerException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private int parseAmount(String amountInput) {
        if (!isIntegerParsable(amountInput)) {
            throw new PlannerException(ExceptionMessage.INVALID_ORDER);
        }
        int amount = Integer.parseInt(amountInput);
        validateAmount(amount);

        return amount;
    }

    private void validateAmount(int amount) {
        if (amount < MIN_AMOUNT_ORDER) {
            throw new PlannerException(ExceptionMessage.INVALID_ORDER);
        }
    }

    private Menu parseMenu(String menuName) {
        Menu menu = Menu.from(menuName);
        if (menu == Menu.NONE) {
            throw new PlannerException(ExceptionMessage.INVALID_ORDER);
        }
        return menu;
    }

    private boolean isIntegerParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void validateDate(String input) {
        if (!isIntegerParsable(input)) {
            throw new PlannerException(ExceptionMessage.INVALID_DATE);
        }
    }

    private void validateDateInMonth(YearMonth month, int day) {
        if (!month.isValidDay(day)) {
            throw new PlannerException(ExceptionMessage.INVALID_DATE);
        }
    }
}
