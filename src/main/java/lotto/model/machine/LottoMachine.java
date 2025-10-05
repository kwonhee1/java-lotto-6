package lotto.model.machine;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.model.lotto.Lotto;
import lotto.model.machine.dto.LottoResultDto;
import lotto.model.machine.vo.WinningLotto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoMachine {
    private int lottoCount;
    private Lotto[] lottoes;

    public LottoMachine(int count) {
        lottoCount = count;
        lottoes = new Lotto[lottoCount];
        for (int i = 0; i < lottoCount; i++) {
            lottoes[i] = createLotto();
        }
    }

    // 1~45, 6자리, 중복 없음
    private Lotto createLotto() {
        return new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
    }

    public Lotto[] getGeneratedLottos() {
        return lottoes;
    }

    public LottoResultDto getResult(WinningLotto winningLotto) {
        Map<LottoResultType, Integer> resultMap = new HashMap<>();
        Arrays.stream(LottoResultType.values()).forEach(resultType -> resultMap.put(resultType, 0));

        for (Lotto eachLotto : lottoes) {
            LottoResultType resultType = eachLotto.getResult(winningLotto.getWinningLotto(), winningLotto.getBonusNumber());
            resultMap.put(resultType, resultMap.get(resultType)+1);
        }
        return new LottoResultDto(lottoCount, resultMap);
    }

    public int getLottoCount() { return lottoCount; }

}
