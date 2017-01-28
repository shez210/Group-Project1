
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;

// The World class holds all game objects and establishes communication among them.
// Handles creation and destruction of entities.
public class World
{
    static ArrayList<GameObject> gameObjects;

    public World()
    {
        gameObjects = new ArrayList<>();

        //If you want to create something, just call some "create" function.
        //createDecoration() is for walls, tiles and map stuff.
        createPlayerBeta();
        createPassiveEnemy();
        //spawnDecoration( Game.resources.textures.get( 2 ), new Vector2f( 400, 100 ) );
    }

    public void update()
    {
        if( Game.inputHandler.isMouseClicked == true )
        {
            spawnProjectile( Game.resources.textures.get( 3 ), gameObjects.get( 0 ).sprite.getPosition(), gameObjects.get( 0 ).motion.direction );
            System.out.println( gameObjects.get( 0 ).motion.direction.toString() );
        }

        destroyObjects();

        for( GameObject object : gameObjects ) { object.update(); }
        checkCollisions();
    }

    public void checkCollisions()
    {
        for( GameObject object1 : gameObjects )
        {
            for( GameObject object2 : gameObjects )
            {
                if( object1.equals( object2 ) == false && isCollision( object1.sprite, object2.sprite ) )
                {
                    //object1.motion.onCollision();
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
        float Ax = A.getGlobalBounds().left;
        float Ay = A.getGlobalBounds().top;
        float Aw = Ax + A.getGlobalBounds().width;
        float Ah = Ay + A.getGlobalBounds().height;

        float Bx = B.getGlobalBounds().left;
        float By = B.getGlobalBounds().top;
        float Bw = Bx + B.getGlobalBounds().width;
        float Bh = By + B.getGlobalBounds().height;

        //System.out.println( A.getGlobalBounds().toString() );
        return ( ( Bx > Ax && Bx < Aw && By > Ay && By < Ah ) || ( Bw > Ax && Bh > Ay && Bw < Aw && Bh < Ah ) );
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

    public static void createPassiveEnemy()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( Game.resources.textures.get( 1 ) ) );
        object.sprite.setPosition( new Vector2f( Game.SCREEN_WIDTH/2, Game.SCREEN_HEIGHT/2 ) );
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
    }

    public static void destroyObjects()
    {
        for( int i = gameObjects.size() - 1; i > 0; i -- )
        {
            if( gameObjects.get( i ).hasMotion() == true && gameObjects.get( i ).motion.isOutOfScreenBoundaries() == true )
            {
                gameObjects.remove( gameObjects.get( i ) );
            }
        }
    }
}
