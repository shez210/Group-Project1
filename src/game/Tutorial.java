package game;
import java.util.ArrayList;

import game.ui.InputHandler;
import game.world.object.GameObject;

import static org.jsfml.window.Keyboard.Key;

public class Tutorial {
	private InputHandler input;
	private ArrayList<Boolean> abilities;

	public Tutorial(InputHandler input, ArrayList<Boolean> abilities) {
		this.input = input;
		this.abilities = abilities;
	}

	// Shaz, the while loop will block the whole game until that button is
	// pressed.
	// Use Clock() to check if a button is pressed within a certain period of
	// time and then change the on-screen text.
	public void update() {
		boolean buttonPressed = false;
		while (buttonPressed = false) {
			System.out.println("Press A to move left");
			abilities.set(GameObject.Ability.MOVE_LEFT.ordinal(), (Boolean) input.keyState.get(Key.A));
			buttonPressed = true;
		}
		while (buttonPressed = false) {
			System.out.println("Press D to move right");
			abilities.set(GameObject.Ability.MOVE_RIGHT.ordinal(), (Boolean) input.keyState.get(Key.D));
			buttonPressed = true;
		}
		while (buttonPressed = false) {
			System.out.println("Press W to move Up");
			abilities.set(GameObject.Ability.MOVE_RIGHT.ordinal(), (Boolean) input.keyState.get(Key.W));
			buttonPressed = true;
		}
		while (buttonPressed = false) {
			System.out.println("Press S to move Down");
			abilities.set(GameObject.Ability.MOVE_RIGHT.ordinal(), (Boolean) input.keyState.get(Key.S));
			buttonPressed = true;
		}
		while (buttonPressed = false) {
			System.out.println("Press S to move Down");
			abilities.set(GameObject.Ability.MOVE_RIGHT.ordinal(), (Boolean) input.keyState.get(Key.S));
			buttonPressed = true;
		}
	}
}
