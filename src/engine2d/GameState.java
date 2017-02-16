package engine2d;


import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

public abstract class GameState
{
    ArrayList<GameObject> gameObjects = new ArrayList<>(); // All game objects in the game are stored here.
    float TOGGLE_DELAY = 1.0f;
    Clock toggleDelayTimer = new Clock();

    abstract void update();
    abstract void draw();

    /**
     * Use this to create tiles, walls, chests, whatever.
     * If you don't know how to use it, see the example in the constructor.
     */
    public void createDecoration( Texture tex, Vector2f pos )
    {
        GameObject object = new GameObject();
        this.gameObjects.add( object );
        object.addTexture( tex );
        object.sprite.setPosition( pos );
        object.sprite.setOrigin( object.sprite.getGlobalBounds().width/2, object.sprite.getGlobalBounds().height/2 );
    }
}
