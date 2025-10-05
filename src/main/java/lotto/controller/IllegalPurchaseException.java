package lotto.controller;

public class IllegalPurchaseException extends IllegalArgumentException {
    private final static String message = "purchase money must be multiple of 1000";
    public IllegalPurchaseException() {
        super(message);
    }
}
