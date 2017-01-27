
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

// The World class holds all game objects and establishes communication between them.
public class World
{
    ArrayList<GameObject> gameObjects;

    public World()
    {
        gameObjects = new ArrayList<>();
        gameObjects.add( new GameObject() );
        createPlayerBeta( gameObjects.get( 0 ) );
        gameObjects.add( new GameObject() );
        createDummy( gameObjects.get( 1 ) );
        gameObjects.add( new GameObject() );
        createDecoration( gameObjects.get( 2 ), Game.resources.textures.get( 2 ) );
    }

    public void update()
    {
        for( GameObject object : gameObjects ) { object.update(); }
        checkCollisions();
    }

    public void checkCollisions()
    {
        for( GameObject object1 : gameObjects )
        {
            for( GameObject object2 : gameObjects )
            {
                if( isCollision( object1.sprite, object2.sprite ) )
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

    public static void createPlayer( GameObject object )
    {
        object.addTexture( new Texture( Game.resources.textures.get( 0 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        //object.addBehaviour( new InputBehaviourOld( Game.inputHandler, object.motion, object.abilities ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 0, 7, 7, 28 ), GameObject.Ability.MOVE_DOWN.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 7, 14, 7, 28 ), GameObject.Ability.MOVE_UP.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 14, 21, 7, 28 ), GameObject.Ability.MOVE_LEFT.ordinal()  );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 21, 28, 7, 28 ), GameObject.Ability.MOVE_RIGHT.ordinal()  );
        object.addBehaviour( new AnimationStateBehaviour( object.anims, object.abilities ) );
    }

    public static void createPlayerBeta( GameObject object )
    {
        object.addTexture( new Texture( Game.resources.textures.get( 1 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite ) );
        object.addBehaviour( new InputBehaviourOld( Game.inputHandler, object.motion, object.sprite, object.abilities ) );
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

    // Use this to create tiles, walls, chests, whatever.
    // If you don't know how to use it, see the example in the constructor.
    public static void createDecoration( GameObject object, Texture tex )
    {
        object.addTexture( tex );
    }

    public static void createBullet()
    {

    }
}
