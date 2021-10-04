package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    @DisplayName("Game Hint 계산 확인" +
            "- 위치와 값이 맞으면 STRIKE" +
            "- 위치가 다르지만 값이 맞으면 BALL" +
            "- 없으면 NONE")
    @ParameterizedTest
    @CsvSource(value = {
            "STRIKE,0,1",
            "STRIKE,1,2",
            "STRIKE,2,3",
            "BALL,0,2",
            "BALL,0,3",
            "BALL,1,3",
            "BALL,2,1",
            "NONE,0,4",
            "NONE,2,5",
    }, delimiter = ',')
    void testCalculateGameHint(GameHint gameHint, int index, int answer) {
        Game game = new Game(Arrays.asList(1, 2, 3));

        assertEquals(gameHint, game.calculateGameHint(index, answer));
    }

}