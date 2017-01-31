package game;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import game.ui.InputHandler;

/**
 * Handles window initialization, settings, holds all resources and records
 * input.
 */
public class Game implements Drawable {
	public static Resources resources = new Resources();

	private World world = new World();

	public void update() {
		world.update();
	}

	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(world);
	}
}