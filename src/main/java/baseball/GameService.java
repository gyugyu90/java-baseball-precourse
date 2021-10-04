package baseball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import nextstep.utils.Randoms;

public class GameService {

    public Game generateNewGame() {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        while (set.size() < 3) {
            set.add(Randoms.pickNumberInRange(1, 9));
        }

        return new Game(new ArrayList<>(set));
    }

    public String calculateHint(Game game, String answer) {
        List<Integer> numbers = game.getNumbers();

        Map<GameHint, Integer> gameHintCounts = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            int number = Integer.parseInt(answer.substring(i, i + 1));
            GameHint gameHint = game.calculateGameHint(i, number);
            gameHintCounts.merge(gameHint, 1, (oldValue, newValue) -> oldValue + 1);
        }

        return convertHintMessage(gameHintCounts);
    }

    private String convertHintMessage(Map<GameHint, Integer> map) {
        if (map.containsKey(GameHint.NONE) && map.get(GameHint.NONE) == 3) {
            return "낫싱";
        }
        if (map.containsKey(GameHint.STRIKE) && map.containsKey(GameHint.BALL)) {
            return String.format("%d스트라이크 %d볼", map.get(GameHint.STRIKE), map.get(GameHint.BALL));
        }
        if (map.containsKey(GameHint.STRIKE)) {
            return String.format("%d스트라이크", map.get(GameHint.STRIKE));
        }
        return String.format("%d볼", map.get(GameHint.BALL));
    }

}
