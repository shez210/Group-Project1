
package engine2d.behaviour;

import engine2d.*;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;

import java.util.ArrayList;

public class NPCStateBehaviour extends StateBehaviour
{
    private Sprite player;
    private float fightingDistance;
    private Clock attackCooldownCounter = new Clock();
    private float attackCooldown = 1.0f;
    private GameObject object;

    public NPCStateBehaviour( GameObject object, Sprite player )
    {
        super( object.sprite, object.motion, object.anims );
        this.object = object;
        this.player = player;
        fightingDistance = player.getGlobalBounds().width/2 + sprite.getGlobalBounds().width/2;
    }

    public void handleInput()
    {

        Vector2f diff = Vector2f.sub( player.getPosition(), sprite.getPosition() );
        float diffX = ( diff.x ) > 0 ? diff.x - fightingDistance : diff.x + fightingDistance;
        Vector2f nearPlayer = Vector2f.add( sprite.getPosition(), new Vector2f( diffX, diff.y ) );

        // Handle state switch.
        switch( currentState )
        {
            case IDLE:
                if( hasArrived( nearPlayer ) == false )
                {
                    currentState = StateBehaviour.State.MOVE;
                }
                if( hasArrived( nearPlayer ) && attackCooldownCounter.getElapsedTime().asSeconds() > 1.0f )
                {
                    currentState = State.ATTACK;
                }
                break;

            case MOVE:
                if( hasArrived( nearPlayer ) && attackCooldownCounter.getElapsedTime().asSeconds() > 1.0f )//&& Math.abs( player.getPosition().y - sprite.getPosition().y ) <= 10.0f )
                {
                    currentState = StateBehaviour.State.ATTACK;
                }
                if( hasArrived( nearPlayer ) && attackCooldownCounter.getElapsedTime().asSeconds() < 1.0f )//&& Math.abs( player.getPosition().y - sprite.getPosition().y ) <= 10.0f )
                {
                    currentState = StateBehaviour.State.IDLE;
                }
                break;

            case ATTACK:
                if( currentAnim.isEnding() )
                {
                    if( hasArrived( nearPlayer ) )
                    {
                        currentState = State.IDLE;
                    }
                    if( hasArrived( nearPlayer ) == false )
                    {
                        currentState = State.MOVE;
                    }
                }
                break;
        }

        if( object.health.get() <= 0 ) { currentState = State.DEAD; }


        //System.out.println( hasArrived(  nearPlayer ));

        if( currentState == State.ATTACK )
        {
            motion.velocity = new Vector2f( 0, 0 );
            attackCooldownCounter.restart();
        }

        if( currentState == State.MOVE )
        {
            motion.velocity = new Vector2f( 0, 0 );
            headTowards( nearPlayer );
            turnTowards( player.getPosition() );
        }

        if( currentState == State.DEAD )
        {
            if( currentAnim.isEnding() )
            {
                object.status = GameObject.Status.INACTIVE;
            }
        }
    }

    /** The point at which the entity will be moving. */
    void headTowards( Vector2f dest )
    {
        Vector2f diff = Vec2f.normalize( Vector2f.sub( dest, sprite.getPosition() ) );
        motion.velocity = new Vector2f( diff.x/2, diff.y/2 );
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
        if( Vec2f.length( diff ) < 5 )
        {
            return true;
        }
        return false;
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
