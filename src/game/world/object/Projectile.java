package game.world.object;

import java.util.LinkedList;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Projectile extends MoveableGameObject {
	private static LinkedList<Projectile> projectilePool = new LinkedList<>();
	
	public Projectile(Texture tex, Vector2f pos, Vector2f direction) {
		addTexture(tex);
		sprite.setPosition(pos);
		velocity = direction;
		type = GameObject.Type.PLAYER_BULLET;
	}

	public void setPosition(Vector2f vector) {
		sprite.setPosition(vector);
	}
	
	public static Projectile newProjectile(Texture tex, Vector2f pos, Vector2f direction) {
		if (projectilePool.size() > 0) {
			Projectile projectile = projectilePool.removeFirst();
			projectile.setPosition(pos);
			projectile.velocity = direction;
			return projectile;
		} else {
			return new Projectile(tex, pos, direction);
		}
	}
	
	public void onCollision(GameObject target) {
		if(target.type == GameObject.Type.ENEMY) {
			target.status = GameObject.Status.DEAD;
		}
	}
	
	public void update() {
		sprite.setPosition(Vector2f.add(sprite.getPosition(), velocity));
	}
}
