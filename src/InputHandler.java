import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;

import java.util.HashMap;
import java.util.Map;

//Handles window events.
public class InputHandler
{
    public Map keyState = new HashMap(); //The map used to store every keyboard key and its current state (true/false).

    // The way to use the map is like this:
    // if( (boolean) input.keyState.get(Key.A) == true ) { doSomething; }
    // All possible keys are in the Keyboard.Key enum. It's available on the JSFML doc here: https://jsfml.sfmlprojects.org/javadoc/index.html?help-doc.html

    InputHandler()
    {
        for( Keyboard.Key i : Keyboard.Key.values() ) { keyState.put( i, false ); }
    }

    void handleWindowEvents()
    {
        // Poll all pending events
        for( Event event : Game.window.pollEvents() ) {
            // If a key is pressed, store the key in the map with a value of 'true'.
            if (event.type == Event.Type.KEY_PRESSED) {
                keyState.put(event.asKeyEvent().key, true);
            }
            // If a key is released, store the key in the map with a value of 'false'.
            if (event.type == Event.Type.KEY_RELEASED) {
                keyState.put(event.asKeyEvent().key, false);
            }

            if (event.type == Event.Type.CLOSED)
            {
                Game.window.close();
            }
        }

        if( ( boolean ) keyState.get( Keyboard.Key.ESCAPE ) == true )
        {
            Game.window.close();
        }


    }
}
