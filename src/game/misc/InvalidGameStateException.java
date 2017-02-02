package game.misc;

@SuppressWarnings("serial")
public class InvalidGameStateException extends Exception {

	public InvalidGameStateException(String string, Exception e) {
		super(string, e);
	}

}
