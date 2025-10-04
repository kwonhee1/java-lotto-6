package lotto;

public class DuplicationLottoNumberException extends LottoException {
    private static final String message = "number can not duplicate";
    public DuplicationLottoNumberException() {
        super(message);
    }
}
