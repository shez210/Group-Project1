package engine2d.behaviour;

import engine2d.App;
import engine2d.GameObject;
import engine2d.World;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

/** Handles movement of entity. */
public class ColliderBehaviour
{
    public Vector2f velocity = new Vector2f( 0, 0 );
    private Sprite sprite;
    private final float SPEED_NORMAL = 1.0f;
    public float speed = SPEED_NORMAL;
    public boolean collidedAlongX;
    public boolean collidedAlongY;
    boolean hasCollidedAlong[] = new boolean[ 2 ];

    public enum Axis{ X, Y }
    
    public ColliderBehaviour( Sprite sprite, float speed )
    {
        this.sprite = sprite;
        this.speed = speed;
    }

    /** Moves the entity to some direction by some velocity. */
    public void update()
    {
        collidedAlongX = false;
        collidedAlongY = false;
        hasCollidedAlong[ Axis.X.ordinal() ] = false;
        hasCollidedAlong[ Axis.Y.ordinal() ] = false;
        //sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) ); // Move the sprite.
    }

    public void inCollisionWith( GameObject object, Axis axis )
    {
        //boolean collision = axis == Axis.X ? collidedAlongX : collidedAlongY;
        if( hasCollidedAlong[ axis.ordinal() ] == false )
        {
            Vector2f move;
            if( axis == Axis.X ) { move = new Vector2f( velocity.x, 0 ); }
            else move = new Vector2f( 0, velocity.y );
            sprite.setPosition( Vector2f.sub( sprite.getPosition(), move ) );
            hasCollidedAlong[ axis.ordinal() ] = true;
        }
    }


    /** Checks if an entity is out of the screen boundaries.
     * @return true if its out of screen, false otherwise.
    */
    public boolean isOutOfScreenBoundaries()
    {
        FloatRect screenRect = new FloatRect( new Vector2f( 0, 0 ), new Vector2f( App.window.getSize() ) );
        return screenRect.intersection( sprite.getGlobalBounds() ) == null;
    }
}
