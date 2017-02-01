package engine2d;

import org.jsfml.graphics.Text;

public class InGame implements GameState
{
    public World world = new World();

    public void update()
    {
        world.update(); // Update all entity logic.
    }

    public void draw()
    {
        world.draw(); // Draw all entities.
        App.window.draw( new Text( "Use WASD to move and mouse to aim/shoot.\nUntil forever...", App.resources.font, 30 ) );
        App.window.draw( App.resources.cursorSprite );
        App.window.display(); // Swap buffers
        App.window.clear(); // Clear the buffer
    }
}
