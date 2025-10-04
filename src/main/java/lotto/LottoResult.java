package lotto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private int totalCount;
    private Map<LottoResultType, Integer> resultMap;

    public LottoResult(int totalCount) {
        this.totalCount = totalCount;
        resultMap = new HashMap<>();
        Arrays.stream(LottoResultType.values()).forEach(resultType -> resultMap.put(resultType, 0));
    }

    public void addResult(LottoResultType resultType) {
        if(resultType.equals(LottoResultType.NO_RESULT))
            return;

        resultMap.put(resultType, resultMap.get(resultType) + 1);
    }

    public double getWinningRate() {
        long totalWinningPrice = 0L;
        for(LottoResultType type : resultMap.keySet()) {
            totalWinningPrice += type.getPrice() * resultMap.get(type);
        }
        return ((double)totalWinningPrice) / (totalCount * 1000) * 100;
    }

    public String getResultStr() {
        StringBuilder str = new StringBuilder();

        str.append(String.format("%s - %d개\n", LottoResultType.THREE.getMessage(), resultMap.get(LottoResultType.THREE)));
        str.append(String.format("%s - %d개\n", LottoResultType.FOUR.getMessage(), resultMap.get(LottoResultType.FOUR)));
        str.append(String.format("%s - %d개\n", LottoResultType.FIVE.getMessage(), resultMap.get(LottoResultType.FIVE)));
        str.append(String.format("%s - %d개\n", LottoResultType.FIVE_AND_BONUS.getMessage(), resultMap.get(LottoResultType.FIVE_AND_BONUS)));
        str.append(String.format("%s - %d개", LottoResultType.SIX.getMessage(), resultMap.get(LottoResultType.SIX)));

        return str.toString();
    }
}
