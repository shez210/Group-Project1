package game;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import game.misc.InvalidGameStateException;
import game.state.GamePlaying;
import game.state.GameStateManager;
import game.ui.InputHandler;

/**
 * Handles window initialization, settings, holds all resources and records
 * input.
 */
public class Game implements Drawable {
	private GameStateManager stateManager;
	
	public Game(Resources resources, InputHandler inputHandler) throws InvalidGameStateException {
		stateManager = new GameStateManager(resources, inputHandler);
		stateManager.setCurrentState(GamePlaying.class);
	}

	public void update() {
		stateManager.updateCurrentState();
	}

	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(stateManager.getCurrentState());
	}
}