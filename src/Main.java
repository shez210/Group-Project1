
import org.jsfml.graphics.Text;
import org.jsfml.system.Clock;

public class Main
{
    public static void main( String args[] )
    {
        Game game   = new Game(); // Don't delete it or game will crash.
        World world = new World();
        Clock timer = new Clock(); // Use it if you want to calculate fps for some reason.

        while( Game.window.isOpen() )
        {
            Game.inputHandler.handleWindowEvents();
            Game.window.clear(); // Clear the buffer
            world.update(); // Update all entity logic.
            world.draw(); // Draw all entities.
            Game.window.draw( new Text( "Use WASD to move and mouse to aim/shoot.\nUntil forever...", Game.resources.font, 30 ) );
            Game.window.display(); // Swap buffers

            //System.out.printf( "framerate is %d\n", 1000/timer.restart().asMilliseconds() );
            //or LockSupport.parkNanos and System.getnanos.
        }
    }
}
