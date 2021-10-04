package baseball;

public class RoundResult {
	private final String hintMessage;
	private final boolean finished;

	public RoundResult(String hintMessage, boolean finished) {
		this.hintMessage = hintMessage;
		this.finished = finished;
	}

	public String getHintMessage() {
		return hintMessage;
	}

	public boolean isFinished() {
		return finished;
	}
}
