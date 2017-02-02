package game.world.object;

import java.util.ArrayList;
import java.util.Collections;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import game.behavior.AnimationBehaviour;
import game.behavior.AnimationStateBehaviour;

// This is the class that is used to instantiate every game object you want.
// There are some "create()" functions in the World class, use them to instantiate.
public class GameObject implements Drawable {
	public Type type;
	public Status status = Status.ALIVE;
	public Sprite sprite;
	public ArrayList<Boolean> abilities;
	public ArrayList<AnimationBehaviour> anims;
	public AnimationStateBehaviour animState;

	public enum Ability {
		MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN, MOVE_ATTACK
	}

	public enum Type {
		PLAYER_BULLET, ENEMY
	}

	public enum Status {
		ALIVE, DEAD
	}

	public GameObject() {
		sprite = new Sprite();
		abilities = new ArrayList<>(Collections.nCopies(Ability.values().length, false));
		anims = new ArrayList<>(Collections.nCopies(Ability.values().length, null));
	}

	public void update() {
		if (animState != null) {
			animState.update();
		} // If the GameObject supports animation, update it.
	}
	

	
	public boolean isDead() {
		return status == Status.DEAD;
	}

	public void addBehaviour(AnimationBehaviour anim, int abilityIndex) {
		this.anims.set(abilityIndex, anim);
	}
	
	void addBehaviour(AnimationBehaviour anim) {
		this.anims.add(anim);
	}

	public void addBehaviour(AnimationStateBehaviour animState) {
		this.animState = animState;
	} 
	public void addTexture(Texture texture) {
		sprite.setTexture(texture);
		sprite.setOrigin(new Vector2f(sprite.getGlobalBounds().width / 2.0f, sprite.getGlobalBounds().height / 2.0f));
	}
	
	public void setPosition(Vector2f position) {
		this.sprite.setPosition(position);
	}
	
	public Vector2f getPosition() {
		return sprite.getPosition();
	}
	
	public boolean isCollidingWith(GameObject target) {
		FloatRect bounds = sprite.getGlobalBounds();
		FloatRect targetBounds = target.sprite.getGlobalBounds();
		FloatRect intersect = bounds.intersection(targetBounds);
		return intersect != null;
	}

	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(sprite);
	}
}
