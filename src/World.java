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
        object.addBehaviour( new AnimationBehaviour( object.sprite, 7, 27 ) );
        object.addBehaviour( new MotionBehaviour( object.sprite, object.abilities ) );
    }
}
