package lotto.view;

public class OutputView {

    public void purchaseResult(Integer count) {
        System.out.println(String.format(OutputViewStr.PURCHASE_RESULT.getMessage(), count));
    }

    public void resultLotto(String lottoResultStr) {
        System.out.println(String.format(OutputViewStr.RESULT_LOTTO.getMessage(), lottoResultStr));
    }

    public void resultRate(Double rate) {
        System.out.println(String.format(OutputViewStr.RESULT_RATE.getMessage(), rate));
    }

    public void generatedlottoes(String lottoStr) {
        System.out.println(lottoStr);
    }

    public void printException(IllegalArgumentException e) {
        System.out.println(String.format(OutputViewStr.ERROR.getMessage(), e.getMessage()));
    }
}
