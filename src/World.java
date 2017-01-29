
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.Random;

// Guys try not to modify my functions without telling me because some other code may break. Just use it, and maybe add new stuff.

/** The World class holds all game objects and establishes communication among them.
 * Handles creation and destruction of entities. */
public class World
{
    static ArrayList<GameObject> gameObjects; // All game objects in the game are stored here.
    static Clock timer = new Clock(); // doesnt belong here
    static Clock timerShoot = new Clock(); // doesnt belong here
    static int playerIndex = -  1; // index of the player object in the array of game objects.

    public World()
    {
        gameObjects = new ArrayList<>(); // create empty arraylist.

        //If you want to create something, just call some "create" function.
        //createDecoration() is for walls, tiles and map stuff.
        createPlayerBeta();
        createEnemyRandom();
    }

    /** Handles creation and destruction of entities and also resolves collision. */
    public void update()
    {
        if( Game.inputHandler.isMouseClicked == true && timerShoot.getElapsedTime().asSeconds() > 0.1f )
        {
            timerShoot.restart();
            createProjectile( Game.resources.textures.get( 3 ), gameObjects.get( 0 ).sprite.getPosition(), gameObjects.get( 0 ).motion.direction );
        }

        if( timer.getElapsedTime().asSeconds() > 0.2f ) { createEnemyRandom(); timer.restart(); }

        for( GameObject object : gameObjects ) { object.update(); }
        resolveCollisions();
        checkForDeallocations();
    }

    public void resolveCollisions()
    {
        for( int i = gameObjects.size() - 1; i > 0; i -- )
        {
            for( int j = gameObjects.size() - 1; j > 0; j -- )
            {
                if( i != j && gameObjects.get( i ).type == GameObject.Type.PLAYER_BULLET.ordinal()
                       && gameObjects.get( j ).type == GameObject.Type.ENEMY.ordinal()
                       && isCollision( gameObjects.get( i ).sprite, gameObjects.get( j ).sprite ) )
                {
                    gameObjects.get( j ).status = GameObject.Status.DEAD.ordinal();
                }
            }
        }
    }

    /** Draws all created game objects on screen. */
    public void draw()
    {
        for( GameObject object : gameObjects ) { Game.window.draw( object.sprite ); }
    }

    /** Checks if there is a collision between two entities.
     *
     * @param A sprite of first entity
     * @param B sprite of second entity
     * @return true if there is a collision, false otherwise.
     */
    public boolean isCollision( Sprite A, Sprite B )
    {
        Vector2f topLeftA = new Vector2f( A.getGlobalBounds().left, A.getGlobalBounds().top );
        Vector2f bottomRightA = new Vector2f( topLeftA.x + A.getGlobalBounds().width, topLeftA.y + A.getGlobalBounds().height );
        Vector2f topLeftB = new Vector2f( B.getGlobalBounds().left, B.getGlobalBounds().top );
        Vector2f bottomRightB = new Vector2f( topLeftB.x + B.getGlobalBounds().width, topLeftB.y + B.getGlobalBounds().height );

        return ( ( Vec2f.greaterThan( topLeftB, topLeftA )     && Vec2f.lessThan( topLeftB, bottomRightA ) ) ||
                 ( Vec2f.greaterThan( bottomRightB, topLeftA ) && Vec2f.lessThan( bottomRightB, bottomRightA ) ) ||
                 ( Vec2f.greaterThan( topLeftA, topLeftB )     && Vec2f.lessThan( topLeftA, bottomRightB ) ) ||
                 ( Vec2f.greaterThan( bottomRightA, topLeftB ) && Vec2f.lessThan( bottomRightA, bottomRightB ) ) );
    }

    /** Creates animated player. Don't use this for now. */
    public static void createPlayer()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( Game.resources.textures.get( 0 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        //object.addBehaviour( new InputBehaviourOld( Game.inputHandler, object.motion, object.abilities ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 0, 7, 7, 28 ), GameObject.Ability.MOVE_DOWN.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 7, 14, 7, 28 ), GameObject.Ability.MOVE_UP.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 14, 21, 7, 28 ), GameObject.Ability.MOVE_LEFT.ordinal()  );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 21, 28, 7, 28 ), GameObject.Ability.MOVE_RIGHT.ordinal()  );
        object.addBehaviour( new AnimationStateBehaviour( object.anims, object.abilities ) );
    }

    /** Creates test player. Uses the placeholder sprite. */
    public static void createPlayerBeta()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( Game.resources.textures.get( 1 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        object.addBehaviour( new InputBehaviourOld( Game.inputHandler, object.motion, object.sprite, object.abilities ) );
        object.sprite.setPosition( 300, 300 );
        playerIndex = gameObjects.size() - 1;
    }

    /** Ignore, I use it for testing sometimes. */
    public static void createDummy()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( Game.resources.textures.get( 0 ) ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 0, 7, 7, 28 ), GameObject.Ability.MOVE_DOWN.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 7, 14, 7, 28 ), GameObject.Ability.MOVE_UP.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 14, 21, 7, 28 ), GameObject.Ability.MOVE_LEFT.ordinal()  );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 21, 28, 7, 28 ), GameObject.Ability.MOVE_RIGHT.ordinal()  );
        object.addBehaviour( new AnimationStateBehaviour( object.anims, object.abilities ) );
        object.sprite.setPosition( new Vector2f( Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2 ) );
    }

    /** Use this to create tiles, walls, chests, whatever.
     * If you don't know how to use it, see the example in the constructor. */
    public static void createDecoration( Texture tex, Vector2f pos )
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( tex );
        object.sprite.setPosition( pos );
    }

    /** Creates a projectile and moves it in a certain direction.
     *
     * @param tex the texture that is going to be used for the projectile.
     * @param pos the starting position of the projectile
     * @param direction the moving direction of the projectile.
     */
    public static void createProjectile( Texture tex, Vector2f pos, Vector2f direction )
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( tex );
        object.sprite.setPosition( pos );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        object.motion.velocity = direction;
        object.type = GameObject.Type.PLAYER_BULLET.ordinal();
    }

    /** Destroy all objects that are out of the screen. */
    public static void destroyOutOfBoundsObjects()
    {

        for( int i = gameObjects.size() - 1; i > 0; i -- ) // Counts backwards for performance reasons.
        {
            if( gameObjects.get( i ).hasMotion() == true && gameObjects.get( i ).motion.isOutOfScreenBoundaries() == true )
            {
                gameObjects.remove( gameObjects.get( i ) );
            }
        }
    }

    /** Deallocate all objects that are already dead. */
    public static void destroyDeadObjects()
    {
        for( int i = gameObjects.size() - 1; i > 0; i -- )
        { if( gameObjects.get( i ).status == GameObject.Status.DEAD.ordinal() ) { gameObjects.remove( gameObjects.get( i ) ); } }
    }

    /** Wrapper function, handles all deallocations. */
    public static void checkForDeallocations()
    {
        destroyOutOfBoundsObjects();
        destroyDeadObjects();
    }

    /** Creates an enemy. Spawn position is across a circle around the screen. */
    public static void createEnemyRandom()
    {
        int angle = new Random().nextInt( 360 );
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( Game.resources.textures.get( 1 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        object.addBehaviour( new AIBehaviour( object.motion, object.sprite, gameObjects.get( 0 ).sprite ) );
        object.sprite.setPosition( new Vector2f( ( ( float ) Math.sin( Math.toRadians( angle ) ) )*500.0f + Game.SCREEN_WIDTH/2, ( ( float ) Math.cos( Math.toRadians( angle ) ) )*500.0f + Game.SCREEN_HEIGHT/2 ) );
        object.type = GameObject.Type.ENEMY.ordinal();
    }
}
