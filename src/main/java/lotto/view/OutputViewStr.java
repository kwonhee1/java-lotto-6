package lotto.view;

public enum OutputViewStr {
    PURCHASE_RESULT("\n%d개를 구매했습니다."),
    RESULT_LOTTO("\n당첨 동계\n---\n%s"),
    RESULT_RATE("총 수익률은 %.1f%%입니다."),
    ERROR("[ERROR] %s");

    private String message;

    OutputViewStr(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
