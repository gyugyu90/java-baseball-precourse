package baseball;


import nextstep.utils.Randoms;

import java.util.*;

public class GameService {

    public Game generateNewGame() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 3) {
            set.add(Randoms.pickNumberInRange(1, 9));
        }

        List<Integer> selectedNumbers = new ArrayList<>(set);
        Collections.shuffle(selectedNumbers);

        return new Game(selectedNumbers);
    }

}
