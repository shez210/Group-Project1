package engine2d.behaviour;

import engine2d.InputHandler;
import engine2d.Vec2f;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/** This input behaviour controls all player actions by mouse click/movement. */
public class InputBehaviourBeta implements InputBehaviour
{
    InputHandler input;
    MotionBehaviour motion;
    Sprite sprite;

    public InputBehaviourBeta( InputHandler input, MotionBehaviour motion, Sprite sprite )
    {
        this.input = input;
        this.motion = motion;
        this.sprite = sprite;
        sprite.setOrigin( new Vector2f( sprite.getGlobalBounds().width/2.0f + 1, sprite.getGlobalBounds().height/2.0f + 1 ) );
    }

    public void update()
    {
        headTowards( input.mouseCoords );
        lookAt( input.mouseCoords );
        System.out.println( motion.velocity.toString() );
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
