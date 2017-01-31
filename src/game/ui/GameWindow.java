package game.ui;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;

import game.Game;
import game.Resources;

public class GameWindow extends RenderWindow {
	private static final String TITLE = "Game";
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	
	private Game game;
	private Cursor cursor;
	private Text text = new Text("Use WASD to move and mouse to aim/shoot.\nUntil forever...", Game.resources.font, 30);
	public static InputHandler inputHandler = new InputHandler();
	
	public GameWindow(Game game, Resources resources) {
		this.game = game;
		this.cursor = new Cursor(new Sprite(resources.textures.get(3)));
		
		
		create(new VideoMode(SCREEN_WIDTH, SCREEN_HEIGHT), TITLE, WindowStyle.DEFAULT);
		setFramerateLimit(120); // Avoid excessive updates
		setMouseCursorVisible(false);
	}
	
	public void update() {
		cursor.setPosition(new Vector2f(inputHandler.mouseCoords));
	}
	
	public void render() {
		clear(); // Clear the buffer
		draw(game); // Draw all entities.
		draw(text);
		draw(cursor);
		display(); // Swap buffers
	}
	
	/** Polls all pending events. */
	public void handleWindowEvents() {

		for (Event event : pollEvents()) {
			// If a key is pressed, store the key in the map with a value of
			// 'true'.
			if (event.type == Event.Type.KEY_PRESSED) {
				inputHandler.keyState.put(event.asKeyEvent().key, true);
			}
			// If a key is released, store the key in the map with a value of
			// 'false'.
			if (event.type == Event.Type.KEY_RELEASED) {
				inputHandler.keyState.put(event.asKeyEvent().key, false);
			}

			if (event.type == Event.Type.MOUSE_MOVED) {
				inputHandler.mouseCoords = event.asMouseEvent().position;
			}

			if (event.type == Event.Type.MOUSE_BUTTON_PRESSED) {
				inputHandler.isMouseClicked = true;
			}
			if (event.type == Event.Type.MOUSE_BUTTON_RELEASED) {
				inputHandler.isMouseClicked = false;
			}

			if (event.type == Event.Type.CLOSED) {
				close();
			}
		}
		if ((boolean) inputHandler.keyState.get(Keyboard.Key.ESCAPE) == true) {
			close();
		}
	}
}
