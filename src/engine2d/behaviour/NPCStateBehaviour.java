
package engine2d.behaviour;

import engine2d.InputHandler;
import engine2d.Utility;
import engine2d.Vec2f;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.ArrayList;

public class NPCStateBehaviour extends StateBehaviour
{
    private Sprite player;
    private float fightingDistance;

    public NPCStateBehaviour( Sprite sprite, MotionBehaviour motion, ArrayList<AnimationBehaviour> anims, Sprite player )
    {
        super( sprite, motion, anims );
        this.player = player;
        fightingDistance = player.getGlobalBounds().width/2 + sprite.getGlobalBounds().width/2;
    }

    public void handleInput()
    {

        Vector2f diff = Vector2f.sub( player.getPosition(), sprite.getPosition() );
        float diffX = ( diff.x ) > 0 ? diff.x - fightingDistance : diff.x + fightingDistance;
        Vector2f nearPlayer = player.getPosition();//Vector2f.add( sprite.getPosition(), new Vector2f( diff.x, diff.y ) );

        // Handle state switch.
        switch( currentState )
        {
            case IDLE:
                if( hasArrived( nearPlayer ) == false )
                {
                    //System.out.println("gogo");
                    currentState = StateBehaviour.State.MOVE;
                }

            case MOVE:
                if( hasArrived( nearPlayer ) )//&& Math.abs( player.getPosition().y - sprite.getPosition().y ) <= 10.0f )
                {
                    currentState = StateBehaviour.State.ATTACK;
                }

            case ATTACK:
                if( currentAnim.isEnding() )
                {
                    if( hasArrived( nearPlayer ) == false )// && Math.abs( player.getPosition().y - sprite.getPosition().y ) > 10.0f )
                    {
                        currentState = StateBehaviour.State.MOVE;
                    }
                }
        }

        if( currentState == State.MOVE )
        {
            headTowards( nearPlayer );
            turnTowards( player.getPosition() );
        }
    }

    /** The point at which the entity will be moving. */
    void headTowards( Vector2f dest )
    {
        Vector2f diff = Vec2f.normalize( Vector2f.sub( dest, sprite.getPosition() ) );
        motion.velocity = diff;
    }

    void turnTowards( Vector2f dest )
    {
        Vector2f diff = Vec2f.normalize( Vector2f.sub( dest, sprite.getPosition() ) );
        if( diff.x < 0.0f ) { Utility.turnLeft( sprite ); }
        else Utility.turnRight( sprite );
    }

    boolean hasArrived( Vector2f dest )
    {
        Vector2f diff = Vector2f.sub( dest, sprite.getPosition() );
        return Vec2f.length( diff ) < 5;
    }

    /** Rotates toward a specified point.
     *
     * @param dest The point at which the entity is going to look at.
     */
    void lookAt( Vector2i dest )
    {
        Vector2f diff = Vector2f.sub( new Vector2f( dest ), sprite.getPosition() );
        sprite.setRotation( ( float ) ( Math.atan2( -diff.x, diff.y ) / Math.PI * 180.0 + 90.0 ) );
    }
}
