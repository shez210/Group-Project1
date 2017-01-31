import game.Resources;
import game.Game;
import game.ui.GameWindow;
import game.ui.InputHandler;

public class Main {
	public static void main(String[] args) {
		Resources resources = new Resources();
		Game game = new Game(); // Don't delete it or game will crash.
		GameWindow window = new GameWindow(game, resources);


		while (window.isOpen()) {
			window.handleWindowEvents();
			game.update();
			window.update();
			window.render();
		}
	}
}
