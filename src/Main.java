
import org.jsfml.system.Clock;

public class Main
{
    public static void main( String args[] )
    {
        Game game   = new Game();
        World world = new World();
        Clock timer = new Clock();

        long fps;

        while( Game.window.isOpen() )
        {
            Game.inputHandler.handleWindowEvents();
            Game.window.clear(); // Clear the buffer
            world.update();
            world.draw();

            Game.window.display(); // Swap buffers
            //System.out.printf( "framerate is %d\n", 1000/timer.restart().asMilliseconds() );
            while( timer.getElapsedTime().asSeconds() < 0.016f )
            {

            }
            //or LockSupport.parkNanos ans System.
        }
    }
}
