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
		String hint = gameService.calculateHint(game, answer);

		System.out.println(hint);
		return "3스트라이크".equals(hint);
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
