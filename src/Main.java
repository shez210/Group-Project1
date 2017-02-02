import game.Resources;
import game.misc.InvalidGameStateException;
import game.Game;
import game.ui.GameWindow;
import game.ui.InputHandler;

public class Main {
	public static void main(String[] args) throws InvalidGameStateException {
		Resources resources = new Resources();
		InputHandler inputHandler = new InputHandler();
		
		Game game = new Game(resources, inputHandler);
		GameWindow window = new GameWindow(game, resources, inputHandler);

		while (window.isOpen()) {
			window.handleWindowEvents();
			game.update();
			window.update();
			window.render();
		}
	}
}
