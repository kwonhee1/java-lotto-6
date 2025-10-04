package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.StringUtil;

public class InputView {

    public int purchase() throws IllegalArgumentException {
        System.out.println(InputViewStr.PURCHASE_MONEY.getMessage());
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public java.util.List<Integer> winningNumber() {
        System.out.println(InputViewStr.WINNING_NUMBER.getMessage());
        String input = Console.readLine();
        return StringUtil.toIntegerList(input);
    }

    public Integer bonusNumber() throws IllegalArgumentException {
        System.out.println(InputViewStr.BONUS_NUMBER.getMessage());
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

}
