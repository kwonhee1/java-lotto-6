package lotto.controller;

import lotto.model.lotto.excpetion.DuplicationLottoNumberException;
import lotto.LottoResult;
import lotto.model.lotto.Lotto;
import lotto.model.machine.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;

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
        Lotto winningLotto = inputWinningNumber();
        Integer bonusNumber = inputBonusNumber(winningLotto);

        // lotto 결과 생성
        LottoResult result =getLottoResultFromMachine(machine, winningLotto, bonusNumber);

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

    private Lotto inputWinningNumber() {
        try {
            return new Lotto(inputView.winningNumber()); // "1 2 3" -> Lotto
        } catch(IllegalArgumentException e) {
            outputView.printException(e);
            return inputWinningNumber();
        }
    }

    private Integer inputBonusNumber(Lotto winningLotto) {
        try {
            Integer bonusNumber = inputView.bonusNumber();
            if (winningLotto.isContainNumber(bonusNumber))
                throw new DuplicationLottoNumberException();
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return inputBonusNumber(winningLotto);
        }
    }

    private LottoResult getLottoResultFromMachine(LottoMachine machine, Lotto winningLotto, Integer bonusNUmber) {
        return machine.getResult(winningLotto, bonusNUmber);
    }

    private void printLottoResult(LottoResult result) {
        outputView.resultLotto(result.getResultStr());
        outputView.resultRate(result.getWinningRate());
    }
}
