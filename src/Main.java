
import org.jsfml.graphics.Text;
import org.jsfml.system.Clock;

public class Main
{
    public static void main( String args[] )
    {
        Game game   = new Game();
        World world = new World();
        Clock timer = new Clock();

        while( Game.window.isOpen() )
        {
            Game.inputHandler.handleWindowEvents();
            Game.window.clear(); // Clear the buffer
            world.update();
            world.draw();

            Game.window.draw( new Text( "Use WASD to move and mouse to aim/shoot.", Game.resources.font, 30 ) );

            Game.window.display(); // Swap buffers
            //System.out.printf( "framerate is %d\n", 1000/timer.restart().asMilliseconds() );

            //or LockSupport.parkNanos and System.getnanos.
        }
    }
}
