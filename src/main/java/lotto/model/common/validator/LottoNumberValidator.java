package lotto.model.common.validator;

import lotto.model.lotto.excpetion.IllegalRangeLottoException;

import java.util.List;

public class LottoNumberValidator {
    public static void validateLottoNumberRange(Integer number) {
        if(!(0 < number && number <= 45))
            throw new IllegalRangeLottoException();
    }

    public static void validateLottoNumbersRange(List<Integer> numbers) {
        for(Integer number : numbers)
            validateLottoNumberRange(number);
    }
}
