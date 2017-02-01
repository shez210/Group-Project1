package engine2d.behaviour;

import engine2d.App;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

/** Handles movement of entity. */
public class MotionBehaviour
{
    public Vector2f velocity;
    public Vector2f direction = new Vector2f( 0, 0 );
    private Sprite sprite;
    private final float SPEED_NORMAL = 2.0f;
    public float speed = SPEED_NORMAL;

    public MotionBehaviour( Sprite sprite )
    {
        this.sprite = sprite;
        velocity = new Vector2f( 0, 0 );
    }

    /** Moves the entity to some direction by some velocity. */
    public void update()
    {
        stayInScreenBoundaries();
        sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) ); // Move the sprite.
    }

    void onCollision()
    {
        System.out.print( "gg" );
    }

    /** Checks if an entity is out of the screen boundaries.
     * @return true if its out of screen, false otherwise.
     */
    public boolean isOutOfScreenBoundaries()
    {
        return ( sprite.getPosition().x < 0 || sprite.getPosition().y < 0 ||
                sprite.getPosition().x > App.SCREEN_WIDTH - sprite.getTextureRect().width ||
                sprite.getPosition().y > App.SCREEN_HEIGHT - sprite.getTextureRect().height );
    }

    /** Check boundaries. Code is not pretty due to the way the JSFML Vector2f class is written.
     * If I find time, I will write another vector class.
     */
    void stayInScreenBoundaries()
    {
        if( sprite.getPosition().x < 0 ) { sprite.setPosition( 0, sprite.getPosition().y ); }
        if( sprite.getPosition().y < 0 ) { sprite.setPosition( sprite.getPosition().x, 0 ); }
        if( sprite.getPosition().x > App.SCREEN_WIDTH - sprite.getTextureRect().width )
        { sprite.setPosition( App.SCREEN_WIDTH - sprite.getTextureRect().width, sprite.getPosition().y ); }
        if( sprite.getPosition().y > App.SCREEN_HEIGHT - sprite.getTextureRect().height )
        { sprite.setPosition( sprite.getPosition().x, App.SCREEN_HEIGHT - sprite.getTextureRect().height );}
    }
}
