package engine2d;


import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

public class Tutorial extends GameState
{
    public Vector2f centerOfScreen = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 );


    public Tutorial()
    {
        App.resources.getSound( "menu" ).play();

        createDecoration( App.resources.props.get( 6 ), centerOfScreen );

    }

    public void update()
    {

        if( ( boolean )App.inputHandler.keyState.get( Keyboard.Key.RETURN ) )
        {
            App.currentState = new World();
        }

        App.resources.cursorSprite.setPosition( new Vector2f( App.inputHandler.mouseCoords ) ); //Set cursor position.
    }

    public void draw()
    {
        App.window.clear();
        for( GameObject object : gameObjects ) { App.window.draw( object.sprite ); }
        App.window.draw( new Text( "\n", App.resources.font, 30 ) );
        App.window.draw( new Text( "\n", App.resources.font, 30 ) );
        App.window.draw( new Text( "\n", App.resources.font, 30 ) );
        App.window.draw( new Text( "\n", App.resources.font, 30 ) );
        App.window.draw( new Text( "\n", App.resources.font, 30 ) );
        App.window.draw( new Text( "                    \n\n\n\n\n                  Use WASD To Move Around", App.resources.font, 30 ) );
        App.window.draw( new Text( "                    \n\n                        Use Space To Attack", App.resources.font, 30 ) );
        App.window.draw( App.resources.cursorSprite );


        App.window.display();
    }
}
