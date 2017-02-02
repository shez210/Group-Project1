package game.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderStates;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

import game.Resources;
import game.ui.GameWindow;
import game.ui.InputHandler;
import game.world.object.Enemy;
import game.world.object.GameObject;
import game.world.object.PlayerBeta;
import game.world.object.Projectile;

/**
 * The World class holds all game objects and establishes communication among them. Handles creation and destruction of entities.
 */
public class World implements Drawable {
	private List<Enemy> enemies = new ArrayList<>();
	private List<Projectile> projectiles = new ArrayList<>();	
	private PlayerBeta player;
	
	private Clock timer = new Clock();
	private Clock timerShoot = new Clock();
	
	private Texture projectileTexture, playerTexture, enemyTexture;
	private InputHandler inputHandler;

	public World(Resources resources, InputHandler inputHandler) {
		this.inputHandler = inputHandler;
		
		projectileTexture = resources.textures.get(3);
		enemyTexture = resources.textures.get(1);
		playerTexture = resources.textures.get(0);
		
		player = new PlayerBeta(enemyTexture, inputHandler);
	}

	/**
	 * Handles creation and destruction of entities and also resolves collision.
	 */
	public void update() {
		handlePlayerProjectile();
		spawnEnemy();
		updateEntities();
		resolveBulletCollisions();
		checkForDeallocations();
	}
	
	private void updateEntities() {
		player.update();
		enemies.forEach(enemy -> enemy.chaseTarget(player));
		projectiles.forEach(Projectile::update);
	}
	
	private void spawnEnemy() {
		if (timer.getElapsedTime().asSeconds() > 0.2f) {
			Enemy enemy = Enemy.newEnemy(enemyTexture, generateRandomPosition(), player.sprite);
			enemies.add(enemy);
			timer.restart();
		}
	}
	
	private void handlePlayerProjectile() {
		if (inputHandler.isMouseClicked && timerShoot.getElapsedTime().asSeconds() > 0.1f) {
			timerShoot.restart();
			projectiles.add(Projectile.newProjectile(projectileTexture, player.getPosition(), player.direction));
		}
	}
	
	private void resolveBulletCollisions() {
		projectiles.forEach(bullet -> enemies.forEach(enemy -> {
			if(bullet.isCollidingWith(enemy)) {
				enemy.status = GameObject.Status.DEAD;
			}
		}));
	}

	private void checkForDeallocations() {
		projectiles.removeIf(Projectile::isOutOfBounds);
		enemies.removeIf(Enemy::isDead);
	}

	private Vector2f generateRandomPosition() {
		int angle = new Random().nextInt(360);
		return new Vector2f(((float) Math.sin(Math.toRadians(angle))) * 500.0f + GameWindow.SCREEN_WIDTH / 2, ((float) Math.cos(Math.toRadians(angle))) * 500.0f + GameWindow.SCREEN_HEIGHT / 2);
	}

	@Override
	public void draw(RenderTarget target, RenderStates states) {
		target.draw(player);
		enemies.forEach(target::draw);
		projectiles.forEach(target::draw);
	}
}
