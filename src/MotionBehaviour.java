
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import java.util.ArrayList;

public class MotionBehaviour
{
    public Vector2f velocity;
    private Sprite sprite;
    private final float SPEED_NORMAL = 0.05f;
    public float speed = SPEED_NORMAL;

    public MotionBehaviour( Sprite sprite )
    {
        this.sprite = sprite;
        velocity = new Vector2f( 0, 0 );
    }

    public void update()
    {
        stayInScreenBoundaries();
        sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) ); // Move the sprite.
    }

    void onCollision()
    {
        System.out.print( "gg" );
    }

    // Check boundaries. Code is not pretty due to the way the JSFML Vector2f class is written.
    // If I find time, I will write another vector class.
    void stayInScreenBoundaries()
    {
        if( sprite.getPosition().x < 0 ) { sprite.setPosition( 0, sprite.getPosition().y ); }
        if( sprite.getPosition().y < 0 ) { sprite.setPosition( sprite.getPosition().x, 0 ); }
        if( sprite.getPosition().x > Game.SCREEN_WIDTH - sprite.getTextureRect().width )
        { sprite.setPosition( Game.SCREEN_WIDTH - sprite.getTextureRect().width, sprite.getPosition().y ); }
        if( sprite.getPosition().y > Game.SCREEN_HEIGHT - sprite.getTextureRect().height )
        { sprite.setPosition( sprite.getPosition().x, Game.SCREEN_HEIGHT - sprite.getTextureRect().height );}
    }
}
