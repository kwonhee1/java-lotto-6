package lotto;

public class IllegalRangeLottoException extends LottoException {
    private static final String message = "lotto number must be between 1 and 45";
    public IllegalRangeLottoException() {
        super(message);
    }
}
