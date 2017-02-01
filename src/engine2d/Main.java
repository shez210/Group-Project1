package engine2d;

import org.jsfml.graphics.Text;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

public class Main
{
    public static void main( String args[] )
    {
        App.init();
        App.currentState = new InGame();

        while( App.window.isOpen() )
        {
            App.inputHandler.handleWindowEvents();
            App.currentState.update();
            App.currentState.draw();

            //Set cursor position.
            App.resources.cursorSprite.setPosition( new Vector2f( App.inputHandler.mouseCoords ) );

            //System.out.printf( "framerate is %d\n", 1000/timer.restart().asMilliseconds() );
            //or LockSupport.parkNanos and System.getnanos.
        }
    }
}
