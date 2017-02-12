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

    /** Rotates toward a specified point.
     *
     * @param dest The point at which the entity is going to look at.
     */
    public void lookAt( Vector2i dest )
    {
        Vector2f diff = Vector2f.sub( new Vector2f( dest ), sprite.getPosition() );
        sprite.setRotation( ( float ) ( Math.atan2( -diff.x, diff.y ) / Math.PI * 180.0 + 90.0 ) );
    }

    /** The point at which the entity will be moving. */
    public void headTowards( Vector2i dest )
    {
        Vector2f diff = Vector2f.sub( new Vector2f( dest ), sprite.getPosition() );
        if( Vec2f.length( diff ) > 20.0f ) { motion.velocity = Vector2f.div( Vec2f.normalize( diff ), 10.0f ); }
        else motion.velocity = new Vector2f( 0, 0 );
    }
}
