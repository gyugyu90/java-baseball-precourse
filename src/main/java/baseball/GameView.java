package baseball;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import nextstep.utils.Console;

public class GameView {

	private static final Pattern pattern = Pattern.compile("[1-9]{3}");
	private static final Set<String> allowedAnswers = new HashSet<>(Arrays.asList("1", "2"));

	private final GameService gameService = new GameService();

	public void run() {
		boolean stopped = false;
		while(!stopped) {
			proceedGame();
			stopped = askMoreGame();
		}
	}

	private void proceedGame() {
		Game game = gameService.generateNewGame();

		boolean matched = false;
		while(!matched) {
			matched = round(game);
		}
		System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
	}

	private boolean round(Game game) {
		System.out.println("숫자를 입력해주세요: ");
		String answer = Console.readLine();
		if (!validate(answer)) {
			return false;
		}

		String hint = gameService.calculateHint(game, answer);
		System.out.println(hint);
		return "3스트라이크".equals(hint);
	}

	private boolean validate(String answer) {
		if (invalidPatternString(answer)) {
			System.err.println("[ERROR] 1~9로 구성된 세자리의 숫자를 입력하셔야 합니다.");
			return false;
		}
		if (containsDuplicateDigits(answer)) {
			System.err.println("[ERROR] 숫자는 서로 다른 숫자를 입력하셔야 합니다.");
			return false;
		}
		return true;
	}

	private boolean invalidPatternString(String source) {
		return !pattern.matcher(source).matches();
	}

	private boolean containsDuplicateDigits(String source) {
		Set<Character> set = new HashSet<>();
		for (char c : source.toCharArray()) {
			set.add(c);
		}
		return set.size() != 3;
	}

	private boolean askMoreGame() {
		String answer = null;
		boolean answered = false;
		while (!answered) {
			System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
			answer = Console.readLine();

			answered = allowedAnswers.contains(answer);
		}
		return "2".equals(answer);
	}

}
