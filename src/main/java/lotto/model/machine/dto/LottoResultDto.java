package lotto.model.machine.dto;

import lotto.model.machine.LottoResultType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResultDto {
    private int totalCount;
    private Map<LottoResultType, Integer> resultMap;

    public LottoResultDto(int totalCount, Map<LottoResultType, Integer> resultMap) {
        this.totalCount = totalCount;
        this.resultMap = resultMap;
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
