package lotto.model.machine.vo;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.excpetion.DuplicationLottoNumberException;
import lotto.model.lotto.excpetion.IllegalRangeLottoException;

import java.util.List;

public class WinningLotto {
    private Lotto winningLotto;
    private Integer bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, Integer bonusNumber) {
        winningLotto = new Lotto(winningNumbers);

        validateBonusNumberRange(bonusNumber);
        validateWinningLottoContainsBonusNumber(winningLotto, bonusNumber);

        this.bonusNumber = bonusNumber;
    }

    private void validateWinningLottoContainsBonusNumber(Lotto winningLotto, Integer bonusNumber) {
        if(winningLotto.isContainNumber(bonusNumber))
            throw new DuplicationLottoNumberException();
    }

    private void validateBonusNumberRange(Integer bonusNumber) {
        if(!(0 < bonusNumber && bonusNumber <= 45))
            throw new IllegalRangeLottoException();
    }

    public Lotto getWinningLotto() { return winningLotto; }
    public Integer getBonusNumber() {return bonusNumber;}
}
