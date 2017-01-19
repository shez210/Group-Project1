import org.jsfml.system.Vector2f;
import static org.jsfml.window.Keyboard.*;

public class InputBehaviour
{
    InputHandler input;
    GameObject entity;

    public InputBehaviour( InputHandler input, GameObject obj )
    {
        this.input = input;
        entity = obj;
    }

    public void update()
    {
        entity.setVelocity( new Vector2f( 0, 0 ) );

        if( ( boolean ) input.keyState.get( Key.A ) == true )
        {
            entity.setVelocity( Vector2f.add( entity.getVelocity(), new Vector2f( -5, 0 ) ) );
        }

        if( ( boolean ) input.keyState.get( Key.D ) == true )
        {
            entity.setVelocity( Vector2f.add( entity.getVelocity(), new Vector2f( 5, 0 ) ) );
        }

        if( ( boolean ) input.keyState.get( Key.W ) == true )
        {
            entity.setVelocity( Vector2f.add( entity.getVelocity(), new Vector2f( 0, -5 ) ) );
        }

        if( ( boolean ) input.keyState.get( Key.S ) == true )
        {
            entity.setVelocity( Vector2f.add( entity.getVelocity(), new Vector2f( 0, 5 ) ) );
        }
    }
}
