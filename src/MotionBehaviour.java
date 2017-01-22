import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

public class MotionBehaviour
{
    ArrayList<Boolean> abilities;
    Vector2f velocity;
    Sprite sprite;

    public MotionBehaviour( Sprite sprite, ArrayList<Boolean> abilities )
    {
        this.sprite = sprite;
        this.abilities = abilities;
        velocity = new Vector2f( 0, 0 );
    }

    public void update()
    {
        velocity = new Vector2f( 0, 0 );

        if( abilities.get( GameObject.Ability.MOVE_LEFT.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( -5, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_RIGHT.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( 5, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_UP.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( 0, -5 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_DOWN.ordinal() ) == true )
        { velocity = Vector2f.add( velocity, new Vector2f( 0, 5 ) ); }

        sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) ); // moves the sprite.
    }
}
