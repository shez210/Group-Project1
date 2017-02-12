package engine2d.behaviour;

import engine2d.GameObject;
import engine2d.InputHandler;
import engine2d.Vec2f;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import java.util.ArrayList;

/** This input behaviour uses key presses to control player. */
public class InputBehaviourOld implements InputBehaviour
{
    private InputHandler input;
    private MotionBehaviour motion;
    private Sprite sprite;

    public InputBehaviourOld( InputHandler input, MotionBehaviour motion, Sprite sprite )
    {
        this.input = input;
        this.motion = motion;
        this.sprite = sprite;
    }

    public void update()
    {

    }
}
