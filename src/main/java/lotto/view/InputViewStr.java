package lotto.view;

public enum InputViewStr {
    PURCHASE_MONEY("구입금액을 입력해 주세요."),
    WINNING_NUMBER("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER("\n보너스 번호를 입력해 주세요."),
    ;

    private String message;

    InputViewStr(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
