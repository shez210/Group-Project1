
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.Random;

// Guys don't touch anything in this class yet, because most of the stuff is hardcoded.

// The World class holds all game objects and establishes communication among them.
// Handles creation and destruction of entities.
public class World
{
    static ArrayList<GameObject> gameObjects;
    static Clock timer = new Clock();
    static Clock timerShoot = new Clock();

    public World()
    {
        gameObjects = new ArrayList<>();

        //If you want to create something, just call some "create" function.
        //createDecoration() is for walls, tiles and map stuff.
        createPlayerBeta();
        spawnEnemyRandom();
        //spawnDecoration( Game.resources.textures.get( 2 ), new Vector2f( 400, 100 ) );
    }

    public void update()
    {
        if( Game.inputHandler.isMouseClicked == true && timerShoot.getElapsedTime().asSeconds() > 0.1f )
        {
            timerShoot.restart();
            spawnProjectile( Game.resources.textures.get( 3 ), gameObjects.get( 0 ).sprite.getPosition(), gameObjects.get( 0 ).motion.direction );
        }

        if( timer.getElapsedTime().asSeconds() > 0.2f ) { spawnEnemyRandom(); timer.restart(); }

        checkForDeallocations();

        for( GameObject object : gameObjects ) { object.update(); }
        checkCollisions();

        //System.out.println( gameObjects.get( 0 ).sprite.getGlobalBounds().toString() );
    }

    public void checkCollisions()
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

    public void draw()
    {
        for( GameObject object : gameObjects ) { Game.window.draw( object.sprite ); }
    }

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

    public static void createPlayerBeta()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( Game.resources.textures.get( 1 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        object.addBehaviour( new InputBehaviourOld( Game.inputHandler, object.motion, object.sprite, object.abilities ) );
        object.sprite.setPosition( 300, 300 );
    }

    public static void createDummy( GameObject object )
    {

        object.addTexture( new Texture( Game.resources.textures.get( 0 ) ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 0, 7, 7, 28 ), GameObject.Ability.MOVE_DOWN.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 7, 14, 7, 28 ), GameObject.Ability.MOVE_UP.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 14, 21, 7, 28 ), GameObject.Ability.MOVE_LEFT.ordinal()  );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 21, 28, 7, 28 ), GameObject.Ability.MOVE_RIGHT.ordinal()  );
        object.addBehaviour( new AnimationStateBehaviour( object.anims, object.abilities ) );
        object.sprite.setPosition( new Vector2f( Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2 ) );
    }

    public static void spawnDummy()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        createDummy( object );
    }

    public static void createPassiveEnemy( GameObject object, Texture tex )
    {
        object.addTexture( tex );

    }

    // Use this to create tiles, walls, chests, whatever.
    // If you don't know how to use it, see the example in the constructor.
    public static void createDecoration( GameObject object, Texture tex, Vector2f pos )
    {
        object.addTexture( tex );
        object.sprite.setPosition( pos );
    }

    public static void spawnDecoration( Texture tex, Vector2f pos )
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        createDecoration( object, tex, pos );
    }

    public static void createProjectile( GameObject object, Texture tex, Vector2f pos, Vector2f direction )
    {
        object.addTexture( tex );
        object.sprite.setPosition( pos );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        object.motion.velocity = direction;
    }

    public static void spawnProjectile( Texture tex, Vector2f pos, Vector2f direction )
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        createProjectile( object, tex, pos, direction );
        object.type = GameObject.Type.PLAYER_BULLET.ordinal();
    }

    public static void destroyOutOfBoundsObjects()
    {
        for( int i = gameObjects.size() - 1; i > 0; i -- )
        {
            if( gameObjects.get( i ).hasMotion() == true && gameObjects.get( i ).motion.isOutOfScreenBoundaries() == true )
            {
                gameObjects.remove( gameObjects.get( i ) );
            }
        }
    }

    public static void deallocateDeadObjects()
    {
        for( int i = gameObjects.size() - 1; i > 0; i -- )
        { if( gameObjects.get( i ).status == GameObject.Status.DEAD.ordinal() ) { gameObjects.remove( gameObjects.get( i ) ); } }
    }

    public static void checkForDeallocations()
    {
        destroyOutOfBoundsObjects();
        deallocateDeadObjects();

    }

    public static void spawnEnemyRandom()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        createPassiveEnemy( object, new Texture( Game.resources.textures.get( 1 ) ) );
        object.sprite.setPosition( new Vector2f( new Random().nextInt( Game.SCREEN_WIDTH ), new Random().nextInt( Game.SCREEN_HEIGHT ) ) );
        object.type = GameObject.Type.ENEMY.ordinal();
    }
}
