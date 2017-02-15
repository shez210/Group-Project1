package engine2d;


import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Menu extends GameState
{
    public Vector2f centerOfScreen = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 );
    public Vector2f newGamePos = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 );
    public Vector2f quitPos = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + 100 );
    public Vector2f gameTitlePos = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 - 180 );


    public Menu()
    {
        //Create a sound and set its buffer
        App.resources.getSound( "menu" ).play();

        createDecoration( App.resources.textures.get( 6 ), centerOfScreen );
        createDecoration( App.resources.textures.get( 7 ), centerOfScreen );
        createDecoration( App.resources.textures.get( 9 ), quitPos );
        createDecoration( App.resources.textures.get( 11 ), gameTitlePos );
    }

    public void update()
    {

        if( ( boolean )App.inputHandler.keyState.get( Keyboard.Key.RETURN ) )
        {
            App.currentState = new World();
        }

        if( App.inputHandler.isMouseOver( gameObjects.get( 1 ).sprite ) )
        {
            gameObjects.get( 1 ).sprite.setTexture( App.resources.textures.get( 8 ) );
            if( App.inputHandler.isMouseClicked )
            {
                App.resources.getSound( "menu" ).stop();
                App.resources.getSound("menu").setLoop(true);
                App.currentState = new World();

            }
        }
        else
        {
            gameObjects.get( 1 ).sprite.setTexture( App.resources.textures.get( 7 ) );
        }

        if( App.inputHandler.isMouseOver( gameObjects.get( 2 ).sprite ) )
        {
            gameObjects.get( 2 ).sprite.setTexture( App.resources.textures.get( 10 ) );
            if( App.inputHandler.isMouseClicked )
            {
                App.window.close();
            }

        }
        else
        {
            gameObjects.get( 2 ).sprite.setTexture( App.resources.textures.get( 9 ) );
        }


        //if(input.isMouseClicked){App.currentState = new World();}
        App.resources.cursorSprite.setPosition( new Vector2f( App.inputHandler.mouseCoords ) ); //Set cursor position.
    }

    public void draw()
    {
        App.window.clear();
        for( GameObject object : gameObjects ) { App.window.draw( object.sprite ); }

        App.window.draw( App.resources.cursorSprite );


        App.window.display();
    }
}
