package game.world.object;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import game.ui.GameWindow;

public class MoveableGameObject extends GameObject {
	public Vector2f velocity = new Vector2f(0, 0);
	public Vector2f direction = new Vector2f(0, 0);
	
	private final float SPEED_NORMAL = 2.0f;
	public float speed = SPEED_NORMAL;
	
	public boolean isOutOfScreenBoundaries() {
		return (sprite.getPosition().x < 0 || sprite.getPosition().y < 0
				|| sprite.getPosition().x > GameWindow.SCREEN_WIDTH - sprite.getTextureRect().width
				|| sprite.getPosition().y > GameWindow.SCREEN_HEIGHT - sprite.getTextureRect().height);
	}
	
	public boolean isOutOfBounds() {
		return isOutOfScreenBoundaries();
	}
}
