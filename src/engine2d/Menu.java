package engine2d;


import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;

import java.util.ArrayList;

public class Menu implements GameState
{
    static ArrayList<GameObject> gameObjects; // All game objects in the game are stored here.
    public Sprite backgroundSprite;

    public Menu()
    {
        gameObjects = new ArrayList<>( 5 );


        createDecoration( App.resources.textures.get( 6 ), new Vector2f( App.SCREEN_WIDTH/2, App.SCREEN_HEIGHT/2 ) );
        createDecoration(App.resources.textures.get(7), new Vector2f(App.SCREEN_WIDTH/2 , App.SCREEN_HEIGHT/2));


    }

    public void update()
    {
        //logic
        if( ( boolean )App.inputHandler.keyState.get( Keyboard.Key.A ) && GameState.toggleDelayTimer.getElapsedTime().asSeconds() > TOGGLE_DELAY )
        {
            App.currentState = new World();
            GameState.toggleDelayTimer.restart();
        }

        if( App.inputHandler.isMouseOver( gameObjects.get( 1 ).sprite ) ) { System.out.print( "asd" ); }
        App.resources.cursorSprite.setPosition( new Vector2f( App.inputHandler.mouseCoords ) ); //Set cursor position.

    }

    public void draw()
    {
        App.window.clear();
        for( GameObject object : gameObjects ) { App.window.draw( object.sprite ); }

        App.window.draw( new Text( "Click On The Buttons...", App.resources.font, 20 ) );
        App.window.draw( App.resources.cursorSprite );

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
