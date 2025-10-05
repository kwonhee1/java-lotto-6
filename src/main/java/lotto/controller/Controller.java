package lotto.controller;

import lotto.model.lotto.excpetion.DuplicationLottoNumberException;
import lotto.LottoResult;
import lotto.model.lotto.Lotto;
import lotto.model.machine.LottoMachine;
import lotto.model.machine.vo.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        // 금액 입력
        int purchaseLottoCount = inputPurchaseLottoCount();

        // lotto machine 준비
        LottoMachine machine = generateLottoMachine(purchaseLottoCount);

        // 발급된 lotto들 출력
        printGeneratedLottoesWithLottoMachine(machine);

        // 당첨 번호 입력
        // winning lotto numbers, bonus number 입력을 한가지 작업으로 고려함
        WinningLotto winningLotto = inputWinningLottoAndBonusNumber();

        // lotto 결과 생성
        LottoResult result =getLottoResultFromMachine(machine, winningLotto);

        // lotto 결과 출력
        printLottoResult(result);
    }

    private Integer inputPurchaseLottoCount() {
        try {
            Integer purchaseMoney = inputView.purchase();

            if (purchaseMoney % 1000 != 0)
                throw new IllegalPurchaseException();
            return purchaseMoney / 1000;
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return inputPurchaseLottoCount();
        }
    }

    private LottoMachine generateLottoMachine(Integer lottoCount) {
        return new LottoMachine(lottoCount);
    }

    private void printGeneratedLottoesWithLottoMachine(LottoMachine lottoMachine){
        outputView.purchaseResult(lottoMachine.getLottoCount());
        outputView.generatedlottoes(LottoesToStr(lottoMachine.getGeneratedLottos()));
    }

    private String LottoesToStr(Lotto[] generatedLottoes) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(generatedLottoes).forEach(l -> builder.append(l.toString()+"\n"));
        return builder.toString();
    }

    private WinningLotto inputWinningLottoAndBonusNumber() {
        try {
            List<Integer> winningNumbers = inputView.winningNumber();
            Integer bonusNumber = inputView.bonusNumber();
            return new WinningLotto(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException e){
            outputView.printException(e);
            return inputWinningLottoAndBonusNumber();
        }
    }

    private LottoResult getLottoResultFromMachine(LottoMachine machine, WinningLotto winningLotto) {
        return machine.getResult(winningLotto);
    }

    private void printLottoResult(LottoResult result) {
        outputView.resultLotto(result.getResultStr());
        outputView.resultRate(result.getWinningRate());
    }
}
