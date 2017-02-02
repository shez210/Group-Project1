package game.world.object;

import java.util.LinkedList;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Enemy extends MoveableGameObject {
	private static LinkedList<Enemy> enemyPool = new LinkedList<>();
	
	public Enemy(ConstTexture texture, Vector2f position, Sprite sprite) {
		addTexture(new Texture(texture));
		this.sprite.setPosition(position);
		type = GameObject.Type.ENEMY;
	}
	

	public static Enemy newEnemy(ConstTexture texture, Vector2f position, Sprite sprite) {
		if (enemyPool.size() > 1) {
			Enemy enemy = enemyPool.removeFirst();
			enemy.status = GameObject.Status.ALIVE;
			enemy.sprite.setPosition(position);
			return enemy;
		} else {
			return new Enemy(texture, position, sprite);
		}
	}
	
	
	public static void storeEnemy(Enemy enemy) {
		if(enemyPool.size() < 50)
			enemyPool.addFirst(enemy);
	}

	/** chases the target that was specified when the constructor was called. */
	public void chaseTarget(GameObject target) {
		Vector2f diff = Vector2f.sub(target.sprite.getPosition(), sprite.getPosition());
		velocity = Vector2f.div(diff, 130.0f);
		sprite.setPosition(Vector2f.add(sprite.getPosition(), velocity));
	}
}
