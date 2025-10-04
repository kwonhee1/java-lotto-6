package lotto.model;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.LottoResult;

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

    public LottoResult getResult(Lotto winning, int bonus) {
        LottoResult result = new LottoResult(lottoCount);
        for (Lotto eachLotto : lottoes) {
            result.addResult(eachLotto.getResult(winning, bonus));
        }
        return result;
    }

    public int getLottoCount() { return lottoCount; }

}
