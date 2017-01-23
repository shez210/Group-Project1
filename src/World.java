
import java.util.ArrayList;

public class World
{
    ArrayList<GameObject> gameObjects;

    public World()
    {
        gameObjects = new ArrayList<>();
        gameObjects.add( new GameObject( Game.resources.textures.get( 0 ) ) );
        createPlayer( gameObjects.get( 0 ) );
    }

    public void update()
    {
        for( GameObject object : gameObjects ) { object.update(); }
    }

    public void draw()
    {
        for( GameObject object : gameObjects ) { Game.window.draw( object.sprite ); }
    }

    public static void createPlayer( GameObject object )
    {
        object.addBehaviour( new InputBehaviour( Game.inputHandler, object.abilities ) );
        object.addBehaviour( new MotionBehaviour( object.sprite, object.abilities ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 0, 7, 7, 28 ), GameObject.Ability.MOVE_DOWN.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 7, 14, 7, 28 ), GameObject.Ability.MOVE_UP.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 14, 21, 7, 28 ), GameObject.Ability.MOVE_LEFT.ordinal()  );
        object.addBehaviour( new AnimationBehaviour( object.sprite, 21, 28, 7, 28 ), GameObject.Ability.MOVE_RIGHT.ordinal()  );
        object.addBehaviour( new AnimationStateBehaviour( object.anims, object.abilities ) );
    }
}
