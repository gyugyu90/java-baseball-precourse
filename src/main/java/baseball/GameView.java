package baseball;

import nextstep.utils.Console;

public class GameView {

	public String getNumberInput() {
		System.out.print("숫자를 입력해주세요: ");
		return Console.readLine();
	}

	public String getGameStop() {
		System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
		return Console.readLine();
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void printErrorMessage(String message) {
		System.err.println("[ERROR] " + message);
	}

}
