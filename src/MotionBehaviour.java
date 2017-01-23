import org.jsfml.graphics.BlendMode;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

public class MotionBehaviour
{
    private ArrayList<Boolean> abilities;
    private Vector2f velocity;
    private Sprite sprite;
    private final int SPEED_NORMAL = 2;

    public MotionBehaviour( Sprite sprite, ArrayList<Boolean> abilities )
    {
        this.sprite = sprite;
        this.abilities = abilities;
    }

    public void update()
    {
        velocity = new Vector2f( 0, 0 );

        if( abilities.get( GameObject.Ability.MOVE_LEFT.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( -SPEED_NORMAL, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_RIGHT.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( SPEED_NORMAL, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_UP.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( 0, -SPEED_NORMAL ) ); }

        if( abilities.get( GameObject.Ability.MOVE_DOWN.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( 0, SPEED_NORMAL ) ); }

        sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) ); // moves the sprite.
    }
}
