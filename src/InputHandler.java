import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

import java.util.HashMap;
import java.util.Map;

public class InputHandler
{
    public Map keyState = new HashMap();

    InputHandler()
    {
        for( Keyboard.Key i : Keyboard.Key.values() ) { keyState.put( i, false ); }
    }

    void handleWindowEvents()
    {
        // Handle any events
        for( Event event : Game.window.pollEvents() )
        {
            if( event.type == Event.Type.KEY_PRESSED ) { keyState.put( event.asKeyEvent().key, true ); }
            if( event.type == Event.Type.KEY_RELEASED ) { keyState.put( event.asKeyEvent().key, false ); }
        }

        if( ( boolean ) keyState.get( Keyboard.Key.ESCAPE ) == true )
        {
            Game.window.close();
        }
    }
}
