package lotto.model.lotto;

import lotto.model.lotto.excpetion.DuplicationLottoNumberException;
import lotto.model.lotto.excpetion.IllegalLengthLottoException;
import lotto.model.lotto.excpetion.IllegalRangeLottoException;
import lotto.model.machine.LottoResultType;

import java.util.List;

public class Lotto {
    private final List<Integer> lottoNumbers;

    public Lotto(List<Integer> lottoNumbers) {
        validateLength(lottoNumbers);
        validateDuplication(lottoNumbers);
        validateLottoNumberRange(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLength(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalLengthLottoException();
        }
    }

    private void validateLottoNumberRange(List<Integer> numbers) {
        for(Integer number : numbers) {
            if(!(0 < number && number <= 45))
                throw new IllegalRangeLottoException();
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        for(int iter = 1; iter < numbers.size(); iter++) {
            if(isDuplication(numbers, 0, iter, numbers.get(iter)))
                throw new DuplicationLottoNumberException();
        }
    }

    private boolean isDuplication(List<Integer> numbers, int start, int end, int value) {
        if(numbers.size() < (end - start))
            return false;

        for(int iter = start; iter < end; iter++){
            if(numbers.get(iter).equals(value))
                return true;
        }
        return false;
    }

    // 맞운 갯수, bonus 여부 > dto, map,
    public LottoResultType getResult(Lotto winning, int bonus) {
        int correctCount = 0;
        boolean isBonus = false;

        for (Integer winningNumber : winning.lottoNumbers) {
            if(isContainNumber(winningNumber))
                correctCount++;
        }

        if(isContainNumber(bonus))
            isBonus = true;

        return LottoResultType.getType(correctCount, isBonus);
    }

    public boolean isContainNumber(int number) {
        for (Integer lottoNumber : lottoNumbers) {
            if(lottoNumber.equals(number))
                return true;
        }
        return false;
    }

    public String toString() {
        return lottoNumbers.toString();
    }
}
