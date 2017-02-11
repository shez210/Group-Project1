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
    private ArrayList<Boolean> abilities;

    public InputBehaviourOld( InputHandler input, MotionBehaviour motion, Sprite sprite, ArrayList<Boolean> abilities )
    {
        this.input = input;
        this.motion = motion;
        this.sprite = sprite;
        this.abilities = abilities;
    }

    public void update()
    {

        abilities.set( GameObject.Ability.MOVE.ordinal(), isMoving() );
        abilities.set( GameObject.Ability.ATTACK.ordinal(), ( Boolean ) input.keyState.get( Keyboard.Key.Q ) );

        motion.velocity = new Vector2f( 0, 0 );

        if( ( boolean )input.keyState.get( Keyboard.Key.A ) )
        {
            if( sprite.getScale().x > 0 )
            {
                sprite.setScale( Vector2f.componentwiseMul( sprite.getScale(), new Vector2f( -1.0f, 1.0f ) ) );
                sprite.setPosition( Vector2f.add( sprite.getPosition(), new Vector2f( sprite.getGlobalBounds().width/2, 0 ) ) );
            }
            motion.velocity = Vector2f.add( motion.velocity, new Vector2f( -motion.speed, 0 ) );
        }

        if( ( boolean )input.keyState.get( Keyboard.Key.D ) )
        {
            if( sprite.getScale().x < 0 )
            {
                sprite.setScale( Vector2f.componentwiseMul( sprite.getScale(), new Vector2f( -1.0f, 1.0f ) ) );
                sprite.setPosition( Vector2f.add( sprite.getPosition(), new Vector2f( -sprite.getGlobalBounds().width/2, 0 ) ) );
            }
            motion.velocity = Vector2f.add( motion.velocity, new Vector2f( motion.speed, 0 ) );
        }

        if( ( boolean )input.keyState.get( Keyboard.Key.W ) )
        { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( 0, -motion.speed ) ); }

        if( ( boolean )input.keyState.get( Keyboard.Key.S ) )
        { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( 0, motion.speed ) ); }

        //lookAt( input.mouseCoords );
    }

    public void lookAt( Vector2i dest )
    {
        Vector2f diff = Vector2f.sub( new Vector2f( dest ), sprite.getPosition() );
        sprite.setRotation( ( float ) ( Math.atan2( -diff.x, diff.y ) / Math.PI * 180.0 + 90.0 ) );
        motion.direction = Vector2f.mul( Vec2f.normalize( diff ), 10.0f );
    }

    public boolean isMoving()
    {
        return ( boolean )input.keyState.get( Keyboard.Key.A ) || ( boolean )input.keyState.get( Keyboard.Key.D ) ||
               ( boolean )input.keyState.get( Keyboard.Key.S ) || ( boolean )input.keyState.get( Keyboard.Key.W );
    }
}
