package lotto;

public class IllegalPurchaseException extends LottoException {
    private final static String message = "purchase money must be multiple of 1000";
    public IllegalPurchaseException() {
        super(message);
    }
}
