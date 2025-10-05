package lotto.model.lotto.excpetion;

import lotto.model.common.LottoException;

public class IllegalLengthLottoException extends LottoException {
    private static final String message = "lotto number size must be 6";
    public IllegalLengthLottoException() {
        super(message);
    }
}
