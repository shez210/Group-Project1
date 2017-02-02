package game.object.notused;

import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import game.world.object.GameObject;

public class Decoration extends GameObject {
	public Decoration(Texture tex, Vector2f pos) {
		addTexture(tex);
		sprite.setPosition(pos);
	}

	@Override
	public void draw(RenderTarget arg0, RenderStates arg1) {
		// TODO Auto-generated method stub
		
	}

}
