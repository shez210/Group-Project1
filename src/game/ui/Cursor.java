package game.ui;
import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

public class Cursor implements Drawable {
	private Sprite sprite;
	
	public Cursor(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setPosition(Vector2f vector2f) {
		sprite.setPosition(vector2f);
	}

	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(sprite);
	}
}
