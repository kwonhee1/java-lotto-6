package lotto.model.machine.vo;

import lotto.model.common.validator.LottoNumberValidator;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.excpetion.DuplicationLottoNumberException;

import java.util.List;

public class WinningLotto {
    private Lotto winningLotto;
    private Integer bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, Integer bonusNumber) {
        winningLotto = new Lotto(winningNumbers);

        LottoNumberValidator.validateLottoNumberRange(bonusNumber);
        validateWinningLottoContainsBonusNumber(winningLotto, bonusNumber);

        this.bonusNumber = bonusNumber;
    }

    private void validateWinningLottoContainsBonusNumber(Lotto winningLotto, Integer bonusNumber) {
        if(winningLotto.isContainNumber(bonusNumber))
            throw new DuplicationLottoNumberException();
    }

    public Lotto getWinningLotto() { return winningLotto; }
    public Integer getBonusNumber() {return bonusNumber;}
}
