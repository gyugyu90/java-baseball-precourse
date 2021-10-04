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

    @Override
    public String toString() {
        return "Game{" +
                "numbers=" + numbers +
                '}';
    }

}
