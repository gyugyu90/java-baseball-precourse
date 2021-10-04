package baseball;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class GameController {

	private static final Pattern pattern = Pattern.compile("[1-9]{3}");

	private static final String GAME_CONTINUE = "1";
	private static final String GAME_STOP = "2";
	private static final Set<String> allowedAnswers = new HashSet<>(Arrays.asList(GAME_CONTINUE, GAME_STOP));

	private final GameService gameService = new GameService();
	private final GameView gameView = new GameView();

	public void run() {
		boolean stopped = false;
		while (!stopped) {
			proceedGame();
			stopped = askStopGame();
		}
	}

	private void proceedGame() {
		Game game = gameService.generateNewGame();

		boolean matched = false;
		while (!matched) {
			matched = round(game);
		}
		gameView.printMessage("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
	}

	private boolean round(Game game) {
		String input = gameView.getNumberInput();

		InputValidation validation = validate(input);
		if (validation.isInvalid()) {
			gameView.printErrorMessage(validation.getMessage());
			return false;
		}

		RoundResult roundResult = gameService.calculateHint(game, input);
		gameView.printMessage(roundResult.getHintMessage());
		return roundResult.isFinished();
	}

	private InputValidation validate(String input) {
		if (invalidPatternString(input)) {
			return InputValidation.invalid("1~9로 구성된 세자리의 숫자를 입력하셔야 합니다.");
		}

		if (containsDuplicateDigits(input)) {
			return InputValidation.invalid("숫자는 서로 다른 숫자를 입력하셔야 합니다.");
		}

		return InputValidation.ok();
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

	private boolean askStopGame() {
		String answer = null;
		boolean answered = false;
		while (!answered) {
			answer = gameView.getGameStop();
			answered = allowedAnswers.contains(answer);
		}
		return GAME_STOP.equals(answer);
	}

}
