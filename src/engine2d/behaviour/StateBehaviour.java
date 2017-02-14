package engine2d.behaviour;

import engine2d.GameObject;
import engine2d.InputHandler;
import engine2d.Utility;
import engine2d.Vec2f;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;

import java.util.ArrayList;


/**
 * Very fragile code. Try not to touch.
 */
public abstract class StateBehaviour
{
    public State currentState = State.IDLE;
    State previousState;
    Sprite sprite;
    MotionBehaviour motion;
    ArrayList<AnimationBehaviour> anims;
    public AnimationBehaviour currentAnim;

    public enum State
    {
        IDLE, MOVE, ATTACK
    }

    public StateBehaviour( Sprite sprite, MotionBehaviour motion, ArrayList<AnimationBehaviour> anims )
    {
        this.sprite = sprite;
        this.motion = motion;
        this.anims = anims;
        this.currentAnim = anims.get( 0 );
    }

    public void changeAnimationTo( State requestedAnim )
    {
        if( currentState != previousState )
        {
            currentAnim = anims.get( requestedAnim.ordinal() );
            currentAnim.reset();
            previousState = currentState;
            System.out.println("animation changed to " + requestedAnim.ordinal());
        }
    }

    abstract void handleInput();

    public void update()
    {
        handleInput();
        changeAnimationTo( currentState );
        currentAnim.update();
    }
}
