package baseball;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameServiceTest {

    private final GameService gameService = new GameService();

    @DisplayName("Game 생성을 하면 중복 없는 난수 3개의 숫자를 만들 수 있어야 함")
    @RepeatedTest(50)
    void testGenerateNewGame() {
        Game game = gameService.generateNewGame();

        Set<Integer> set = new HashSet<>(game.getNumbers());
        System.out.println(game);
        assertEquals(3, set.size());
    }

    @DisplayName("hint 메시지는 다음과 같이 생성되어야 함"
        + "스트라이크, 볼이 없을 경우 -> 낫싱"
        + "스트라이크와 볼이 모두 있을 경우 -> `N스트라이크 M볼`"
        + "스트라이크 혹은 볼만 있을 경우 -> `N스트라이크` OR `N볼`")
    @ParameterizedTest
    @CsvSource(value = {
        "3스트라이크,423",
        "2스트라이크,421",
        "1스트라이크 2볼,324",
        "1스트라이크 1볼,328",
        "1스트라이크,418",
        "2볼,235",
        "1볼,276",
        "낫싱,179"
    }, delimiter = ',')
    void testCalculateHint(String expectedHint, String answer) {
        Game game = new Game(Arrays.asList(4, 2, 3));

        assertEquals(expectedHint, gameService.calculateHint(game, answer));
    }
}