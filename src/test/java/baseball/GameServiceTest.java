package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}