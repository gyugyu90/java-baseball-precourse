package baseball;

public class InputValidation {

	private final String message;
	private final boolean valid;

	private InputValidation(String message, boolean valid) {
		this.message = message;
		this.valid = valid;
	}

	public static InputValidation ok() {
		return new InputValidation("", true);
	}

	public static InputValidation invalid(String message) {
		return new InputValidation(message, false);
	}

	public boolean isInvalid() {
		return !valid;
	}

	public String getMessage() {
		return message;
	}
}
