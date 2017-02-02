package game.world.object;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;

import game.misc.Vec2f;
import game.ui.InputHandler;

public class PlayerBeta extends MoveableGameObject {
	private InputHandler input;

	public PlayerBeta(ConstTexture texture, InputHandler input) {
		this.input = input;
		
		addTexture(new Texture(texture));
		sprite.setPosition(300, 300);
		

	}

	public void lookAt(Vector2i dest) {
		Vector2f diff = Vector2f.sub(new Vector2f(dest), sprite.getPosition());
		sprite.setRotation((float) (Math.atan2(-diff.x, diff.y) / Math.PI * 180.0 + 90.0));
		direction = Vector2f.mul(Vec2f.normalize(diff), 10.0f);
	}
	
	public void update() {
		velocity = new Vector2f(0, 0);
		abilities.set(GameObject.Ability.MOVE_LEFT.ordinal(), (Boolean) input.keyState.get(Keyboard.Key.A));
		abilities.set(GameObject.Ability.MOVE_RIGHT.ordinal(), (Boolean) input.keyState.get(Keyboard.Key.D));
		abilities.set(GameObject.Ability.MOVE_UP.ordinal(), (Boolean) input.keyState.get(Keyboard.Key.W));
		abilities.set(GameObject.Ability.MOVE_DOWN.ordinal(), (Boolean) input.keyState.get(Keyboard.Key.S));
		if (abilities.get(GameObject.Ability.MOVE_LEFT.ordinal()) == true) {
			velocity = Vector2f.add(velocity, new Vector2f(-speed, 0));
		}

		if (abilities.get(GameObject.Ability.MOVE_RIGHT.ordinal()) == true) {
			velocity = Vector2f.add(velocity, new Vector2f(speed, 0));
		}

		if (abilities.get(GameObject.Ability.MOVE_UP.ordinal()) == true) {
			velocity = Vector2f.add(velocity, new Vector2f(0, -speed));
		}

		if (abilities.get(GameObject.Ability.MOVE_DOWN.ordinal()) == true) {
			velocity = Vector2f.add(velocity, new Vector2f(0, speed));
		}


		sprite.setPosition(Vector2f.add(sprite.getPosition(), velocity));
		lookAt(input.mouseCoords);

	}
}
