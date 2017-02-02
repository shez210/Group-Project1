package game.state;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.jsfml.graphics.Drawable;

import game.Resources;
import game.misc.InvalidGameStateException;
import game.ui.InputHandler;

public class GameStateManager {
	private static HashMap<Class<? extends GameState>, GameState> stateMap = new HashMap<>();
	private Resources resources;
	private InputHandler inputHandler;
	
	private GameState currentState;

	public GameStateManager(Resources resources, InputHandler inputHandler) {
		this.resources = resources;
		this.inputHandler = inputHandler;
	}

	public void setCurrentState(Class<? extends GameState> stateType) throws InvalidGameStateException {
		GameState state = stateMap.get(stateType);
		if (state == null) {
			try {
				Constructor<? extends GameState> constructor = stateType.getConstructor(Resources.class, InputHandler.class);

				try {
					stateMap.put(stateType, state = constructor.newInstance(resources, inputHandler));
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new InvalidGameStateException("Game state set to non-existing state", e);
				}
			} catch (NoSuchMethodException | SecurityException e) {
				throw new InvalidGameStateException("Game state chosen does not have public constructor with Resource & InputHandler parameters.", e);
			}
		}
		
		currentState = state;
	}

	public void updateCurrentState() {
		currentState.update(this);
	}

	public GameState getCurrentState() {
		return currentState;
	}
}
