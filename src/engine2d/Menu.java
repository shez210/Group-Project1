package engine2d;


import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

import java.util.ArrayList;

public class Menu implements GameState
{
    static ArrayList<GameObject> gameObjects; // All game objects in the game are stored here.
    public Sprite backgroundSprite;

    public Menu()
    {
        gameObjects = new ArrayList<>( 5 );


        createDecoration( App.resources.textures.get( 6 ), new Vector2f( App.SCREEN_WIDTH/2, App.SCREEN_HEIGHT/2 ) );
        backgroundSprite = new Sprite( App.resources.textures.get( 6 ) );
        backgroundSprite.setScale(0.5f, 0.5f);


    }

    public void update()
    {
        //logic
        if( ( boolean )App.inputHandler.keyState.get( Keyboard.Key.A ) && GameState.toggleDelayTimer.getElapsedTime().asSeconds() > TOGGLE_DELAY )
        {
            App.currentState = new World();
            GameState.toggleDelayTimer.restart();
        }
    }

    public void draw()
    {
        App.window.clear();
        for( GameObject object : gameObjects ) { App.window.draw( object.sprite ); }
        App.window.display();
    }

    /** Use this to create tiles, walls, chests, whatever.
     * If you don't know how to use it, see the example in the constructor. */
    public static void createDecoration(Texture tex, Vector2f pos )
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( tex );
        object.sprite.setPosition( pos );
    }
}
