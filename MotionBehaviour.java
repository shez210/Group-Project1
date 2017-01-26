
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import java.util.ArrayList;

public class MotionBehaviour
{
    private ArrayList<Boolean> abilities;
    private Vector2f velocity;
    private Sprite sprite;
    private final float SPEED_NORMAL = 0.025f;
    private float speed = SPEED_NORMAL;

    public MotionBehaviour( Sprite sprite, ArrayList<Boolean> abilities )
    {
        this.sprite = sprite;
        this.abilities = abilities;
    }

    public void update()
    {
        velocity = new Vector2f( 0, 0 );
        //speed = Game.deltaTime*SPEED_NORMAL;

        if( abilities.get( GameObject.Ability.MOVE_LEFT.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( -speed, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_RIGHT.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( speed, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_UP.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( 0, -speed ) ); }

        if( abilities.get( GameObject.Ability.MOVE_DOWN.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( 0, speed ) ); }

        stayInScreenBoundaries();

        sprite.setPosition( Vector2f.add( new Vector2f( sprite.getPosition().x, sprite.getPosition().y ), velocity ) ); // Move the sprite.
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
