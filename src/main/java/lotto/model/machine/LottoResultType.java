package lotto.model.machine;

public enum LottoResultType {
    SIX("6개 일치 (%,d원)", 2000000000L),
    FIVE_AND_BONUS("5개 일치, 보너스 볼 일치 (%,d원)", 30000000L),
    FIVE("5개 일치 (%,d원)", 1500000L),
    FOUR("4개 일치 (%,d원)", 50000L),
    THREE("3개 일치 (%,d원)", 5000L),
    NO_RESULT("3개 이하 일치 (%,d원)", 0L)
    ;

    private long price;
    private String message;

    LottoResultType(String message, long price){
        this.price = price; this.message = message;
    }

    public String getMessage() { return String.format(message, price); }
    public long getPrice() {return price; }

    public static LottoResultType getType(int correctCount, boolean isBonus) {
        if(correctCount == 6)
            return LottoResultType.SIX;

        if(correctCount == 5 && isBonus)
            return LottoResultType.FIVE_AND_BONUS;

        if(correctCount == 5)
            return LottoResultType.FIVE;

        if(correctCount == 4)
            return LottoResultType.FOUR;

        if(correctCount == 3)
            return LottoResultType.THREE;

        return LottoResultType.NO_RESULT;
    }
}
