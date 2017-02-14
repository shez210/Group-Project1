
package engine2d.behaviour;

import engine2d.InputHandler;
import engine2d.Utility;
import engine2d.Vec2f;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;

import java.util.ArrayList;

public class PlayerStateBehaviour extends StateBehaviour
{
    InputHandler input;

    public PlayerStateBehaviour( InputHandler input, Sprite sprite, MotionBehaviour motion, ArrayList<AnimationBehaviour> anims )
    {
        super( sprite, motion, anims );
        this.input = input;
    }

    public void handleInput()
    {
        // Handle state switch.
        switch( currentState )
        {
            case IDLE:
                if( ( boolean )input.keyState.get( Keyboard.Key.A ) || ( boolean )input.keyState.get( Keyboard.Key.D )
                ||  ( boolean )input.keyState.get( Keyboard.Key.W ) || ( boolean )input.keyState.get( Keyboard.Key.S ) )
                {
                    currentState = StateBehaviour.State.MOVE;
                }
                if( ( boolean )input.keyState.get( Keyboard.Key.Q ) )
                {
                    currentState = StateBehaviour.State.ATTACK;
                }

            case MOVE:
                if( input.keyState.containsValue( true ) == false )
                {
                    currentState = StateBehaviour.State.IDLE;
                }
                if( ( boolean )input.keyState.get( Keyboard.Key.Q ) )
                {
                    currentState = StateBehaviour.State.ATTACK;
                }
            case ATTACK:
                if( currentAnim.isEnding() )
                {
                    if( ( boolean )input.keyState.get( Keyboard.Key.A ) || ( boolean )input.keyState.get( Keyboard.Key.D )
                    ||  ( boolean )input.keyState.get( Keyboard.Key.W ) || ( boolean )input.keyState.get( Keyboard.Key.S ) )
                    {
                        currentState = StateBehaviour.State.MOVE;
                    }

                    else if( input.keyState.containsValue( true ) == false )
                    {
                        currentState = StateBehaviour.State.IDLE;
                    }
                }
        }

        if( currentState == StateBehaviour.State.MOVE )
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
        }
    }
}
