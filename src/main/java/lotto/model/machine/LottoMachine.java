package lotto.model.machine;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.LottoResult;
import lotto.model.lotto.Lotto;

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
    // 차라리 builder class를 두어서라도 dto의 불변을 유지하자
    // map변수를 두고 LottoResult에게 생성자로 넘겨주는 것! -> 이게 제일 자연스러워 보임

    public int getLottoCount() { return lottoCount; }

}
