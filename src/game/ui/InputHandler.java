package game.ui;

import java.util.HashMap;
import java.util.Map;

import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;

/** Handles window, keyboard, mouse events. */
public class InputHandler {
	public Map keyState = new HashMap(); // The map used to store every keyboard key and its current state (true/false).
	public Vector2i mouseCoords = new Vector2i(1, 1); // Don't set to 0. Vector2.div() returns NaN if 0 in division.
	public boolean isMouseClicked;
	

	/**
	 * The way to use the map is like this: if( (boolean) input.keyState.get(Key.A) == true ) { doSomething; } All possible keys are in the Keyboard.Key enum. It's available on the JSFML doc here: https://jsfml.sfmlprojects.org/javadoc/index.html?help-doc.html
	 */
	public InputHandler() {
		for (Keyboard.Key i : Keyboard.Key.values()) {
			keyState.put(i, false);
		}
	}


}
