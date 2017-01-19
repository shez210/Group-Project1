import org.jsfml.system.Clock;

public class Main
{
    public static void main( String[] args )
    {
        Game game = new Game();
        GameObject object = new GameObject( game.resources.textures.get( 0 ) );
        Clock timer = new Clock();
        while( game.window.isOpen() )
        {
            Game.window.setFramerateLimit( 120 ); // Avoid excessive updates
            game.inputHandler.handleWindowEvents();
            object.update();
            game.window.clear(); // Clear the buffer
            game.window.draw( object.sprite );
            game.window.display(); // Swap buffers
            System.out.printf( "framerate is %d\n", 1000/timer.restart().asMilliseconds() );

        }
    }
}
