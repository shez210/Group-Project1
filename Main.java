import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

public class Main
{
    public static void main( String[] args )
    {
        Game game = new Game();
        GameObject object = new GameObject( game.resources.textures.get( 0 ) );
        Clock timer = new Clock();
        while( game.window.isOpen() )
        {
            game.inputHandler.handleWindowEvents();
            object.update();
            game.window.clear(); // Clear the buffer
            game.window.draw( object.sprite );
            //Game.window.setFramerateLimit( 60 ); // Avoid excessive updates
            game.window.display(); // Swap buffers
            //System.out.printf( "framerate is %d\n", 1000/timer.restart().asMilliseconds() );
        }
    }
}
