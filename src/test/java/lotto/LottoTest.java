package lotto;

import lotto.model.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호의 개수가 6개보다 작으면 예외가 발생한다.")
    @Test
    void createLottoByLowSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1부터 45 사이의 숫자만 존재 할 수 있다")
    @Test
    void createLottoByStrangeNumber() {
        Assertions.assertAll(
                ()->assertThatThrownBy(() -> { new Lotto(List.of(0,-1,-2,-3,-4,-5)); })
                        .isInstanceOf(IllegalArgumentException.class),
                ()->assertThatThrownBy(()->new Lotto(List.of(46,47,48,49,50,60)))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @DisplayName("로또의 getResult가 정상 작동하는지 확인")
    @Test
    void getResultTest() {
        Lotto winning = new Lotto(List.of(1,2,3,4,5,6));
        Integer bonus = 7;

        Lotto allCorrectLotto = new Lotto(List.of(1,2,3,4,5,6));
        Lotto bonusCorrectLotto = new Lotto(List.of(1,2,3,4,5,7));

        Assertions.assertAll(
                ()->Assertions.assertEquals(LottoResultType.SIX, allCorrectLotto.getResult(winning, bonus)),
                ()->Assertions.assertEquals(LottoResultType.FIVE_AND_BONUS, bonusCorrectLotto.getResult(winning, bonus))
        );
    }

}