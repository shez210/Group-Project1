package engine2d.behaviour;

import engine2d.GameObject;
import engine2d.InputHandler;
import engine2d.Utility;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class handles transitions between different animations.
 * You don't have to know how it works, just use it.
 */
public class StateBehaviour
{
    public State currentState = State.IDLE;
    public State previousState = State.MOVE;
    private InputHandler input;
    private Sprite sprite;
    private MotionBehaviour motion;

    private ArrayList<AnimationBehaviour> anims;
    public AnimationBehaviour currentAnim;
    private int currentAnimIndex;
    private int currentAnimType;

    public enum State
    {
        IDLE, MOVE, ATTACK
    }

    public StateBehaviour( InputHandler input, Sprite sprite, MotionBehaviour motion, ArrayList<AnimationBehaviour> anims )
    {
        this.input = input;
        this.sprite = sprite;
        this.motion = motion;
        this.anims = anims;
        this.currentAnim = anims.get( 0 );
    }

    public void changeAnimTo( int requestedAnimType, int flag )
    {
        if( requestedAnimType != currentAnimIndex ) // If requested anim is different that the current one.
        {
            if( currentAnimType == AnimationBehaviour.animType.INTERRUPTABLE.ordinal() || ( currentAnimType == AnimationBehaviour.animType.NON_INTERRUPTABLE.ordinal() && currentAnim.isEnding() ) )
            {
                currentAnimIndex = requestedAnimType;
                currentAnim = anims.get( requestedAnimType );
                currentAnim.reset();
                currentAnimType = flag;
            }
        }
    }

    public void changeAnimationTo( State requestedAnim )
    {
        if( currentState != previousState )
        {
            currentAnim = anims.get( requestedAnim.ordinal() );
            currentAnim.reset();
            previousState = currentState;
        }
    }

    public void update()
    {
        // Handle state switch.
        switch( currentState )
        {
            case IDLE:
                if( ( boolean )input.keyState.get( Keyboard.Key.A ) || ( boolean )input.keyState.get( Keyboard.Key.D )
                ||  ( boolean )input.keyState.get( Keyboard.Key.W ) || ( boolean )input.keyState.get( Keyboard.Key.S ) )
                {
                    currentState = State.MOVE;
                }
                if( ( boolean )input.keyState.get( Keyboard.Key.Q ) )
                {
                    currentState = State.ATTACK;
                }

            case MOVE:
                if( input.keyState.containsValue( true ) == false )
                {
                    currentState = State.IDLE;
                }
                if( ( boolean )input.keyState.get( Keyboard.Key.Q ) )
                {
                    currentState = State.ATTACK;
                }
            case ATTACK:
                if( currentAnim.isEnding() )
                {
                    if( ( boolean )input.keyState.get( Keyboard.Key.A ) || ( boolean )input.keyState.get( Keyboard.Key.D )
                    ||  ( boolean )input.keyState.get( Keyboard.Key.W ) || ( boolean )input.keyState.get( Keyboard.Key.S ) )
                    {
                        currentState = State.MOVE;
                    }

                    else if( input.keyState.containsValue( true ) == false )
                    {
                        currentState = State.IDLE;
                    }
                }
        }

        if( currentState == State.MOVE )
        {
            if( ( boolean )input.keyState.get( Keyboard.Key.A ) )
            {
                if( sprite.getScale().x > 0 ) { Utility.turnLeft( sprite ); }
                motion.velocity = Vector2f.add( motion.velocity, new Vector2f( -motion.speed, 0 ) );
            }

            if( ( boolean )input.keyState.get( Keyboard.Key.D ) )
            {
                if( sprite.getScale().x < 0 ) { Utility.turnRight( sprite ); }
                motion.velocity = Vector2f.add( motion.velocity, new Vector2f( motion.speed, 0 ) );
            }

            if( ( boolean )input.keyState.get( Keyboard.Key.W ) )
            { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( 0, -motion.speed ) ); }

            if( ( boolean )input.keyState.get( Keyboard.Key.S ) )
            { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( 0, motion.speed ) ); }
            changeAnimationTo( State.MOVE );
        }

        changeAnimationTo( currentState );

        currentAnim.update();
    }
}
