package baseball;

import java.util.Collections;
import java.util.List;

public final class Game {

    private final List<Integer> numbers;

    public Game(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public GameHint calculateGameHint(int index, int answer) {
        if (numbers.get(index).equals(answer)) {
            return GameHint.STRIKE;
        }

        if (numbers.contains(answer)) {
            return GameHint.BALL;
        }

        return GameHint.NONE;
    }

    @Override
    public String toString() {
        return "Game{" +
                "numbers=" + numbers +
                '}';
    }

}
