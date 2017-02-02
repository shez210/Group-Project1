package game.state;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;

import game.Resources;
import game.ui.InputHandler;
import game.world.World;

public class GamePlaying extends GameState {
	private World world;
	
	public GamePlaying(Resources resources, InputHandler inputHandler) {
		super(resources, inputHandler);
		
		this.world = new World(resources, inputHandler);
	}

	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(world);
	}

	@Override
	public void update(GameStateManager stateManager) {
		world.update();
	}
}
