package game.state;

import org.jsfml.graphics.Drawable;

import game.Resources;
import game.ui.InputHandler;

public abstract class GameState implements Drawable {
	private InputHandler inputHandler;
	private Resources resources;

	public GameState(Resources resources, InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		this.resources = resources;
	}

	public abstract void update(GameStateManager stateManager);
}
