package engine2d.behaviour;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class StateMachine
{
    RenderWindow window;
    ScreenInformation screenInformation;

    public StateMachine(RenderWindow window, ScreenInformation screenInformation)
    {
        this.window = window;
        this.screenInformation = screenInformation;
    }

    public Vector2f twoItotwoF(Vector2i vector2i)
    {
        return new Vector2f((float) vector2i.x, (float) vector2i.y);
    }
}
