package lotto.controller;

import lotto.DuplicationLottoNumberException;
import lotto.IllegalPurchaseException;
import lotto.LottoResult;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private LottoMachine machine;

    public Controller() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        // 금액 입력
        int purchaseLottoCount = inputPurchaseLottoCount();

        // lotto machine 준비
        machine = new LottoMachine(purchaseLottoCount);

        // 발급된 lotto들 출력
        outputView.purchaseResult(machine.getLottoCount());
        outputView.generatedlottoes(LottoesToStr(machine.getGeneratedLottos()));

        // 당첨 번호 입력
        Lotto winningLotto = inputWinningNumber();
        Integer bonusNumber = inputBonusNumber(winningLotto);

        // lotto 결과 생성
        LottoResult result = machine.getResult(winningLotto, bonusNumber);

        // lotto 결과 출력
        outputView.resultLotto(result.getResultStr());
        outputView.resultRate(result.getWinningRate());
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

    private String LottoesToStr(Lotto[] generatedLottoes) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(generatedLottoes).forEach(l -> builder.append(l.toString()+"\n"));
        return builder.toString();
    }
}
