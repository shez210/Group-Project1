package engine2d.behaviour;

import engine2d.App;
import engine2d.GameObject;
import engine2d.World;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/** Handles movement of entity. */
public class MotionBehaviour
{
    public Vector2f velocity = new Vector2f( 0, 0 );
    private Sprite sprite;
    private final float SPEED_NORMAL = 1.0f;
    public float speed = SPEED_NORMAL;
    public boolean collidedAlongX;
    public boolean collidedAlongY;
    
    public MotionBehaviour( Sprite sprite, float speed )
    {
        this.sprite = sprite;
        this.speed = speed;
    }

    /** Moves the entity to some direction by some velocity. */
    public void update()
    {
        collidedAlongX = false;
        collidedAlongY = false;
        //sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) ); // Move the sprite.
    }

    public void inCollisionXWith( GameObject object )
    {
        if( World.isCollision( sprite.getGlobalBounds(), object.sprite.getGlobalBounds() ) && collidedAlongX == false )
        {
            sprite.setPosition( Vector2f.sub( sprite.getPosition(), new Vector2f( velocity.x, 0 ) ) );
            collidedAlongX = true;
        }
    }

    public void inCollisionYWith( GameObject object )
    {
        if( World.isCollision( sprite.getGlobalBounds(), object.sprite.getGlobalBounds() ) && collidedAlongY == false )
        {
            sprite.setPosition( Vector2f.sub( sprite.getPosition(), new Vector2f( 0, velocity.y ) ) );
            collidedAlongY = true;
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

    /** Check boundaries. Code is not pretty due to the way the JSFML Vector2f class is written.
     * If I find time, I will write another vector class.

    void stayInScreenBoundaries()
    {
        if( sprite.getPosition().x < 0 ) { sprite.setPosition( 0, sprite.getPosition().y ); }
        if( sprite.getPosition().y < 0 ) { sprite.setPosition( sprite.getPosition().x, 0 ); }
        if( sprite.getPosition().x > App.SCREEN_WIDTH - sprite.getTextureRect().width )
        { sprite.setPosition( App.SCREEN_WIDTH - sprite.getTextureRect().width, sprite.getPosition().y ); }
        if( sprite.getPosition().y > App.SCREEN_HEIGHT - sprite.getTextureRect().height )
        { sprite.setPosition( sprite.getPosition().x, App.SCREEN_HEIGHT - sprite.getTextureRect().height );}
    }
    */
}
