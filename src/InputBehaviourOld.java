
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import java.util.ArrayList;

public class InputBehaviourOld implements InputBehaviour
{
    private InputHandler input;
    private MotionBehaviour motion;
    private ArrayList<Boolean> abilities;

    public InputBehaviourOld( InputHandler input, MotionBehaviour motion, ArrayList<Boolean> abilities )
    {
        this.input = input;
        this.motion = motion;
        this.abilities = abilities;
    }

    public void update()
    {
        abilities.set( GameObject.Ability.MOVE_LEFT.ordinal(), ( Boolean ) input.keyState.get( Keyboard.Key.A ) );
        abilities.set( GameObject.Ability.MOVE_RIGHT.ordinal(), ( Boolean ) input.keyState.get( Keyboard.Key.D ) );
        abilities.set( GameObject.Ability.MOVE_UP.ordinal(), ( Boolean ) input.keyState.get( Keyboard.Key.W ) );
        abilities.set( GameObject.Ability.MOVE_DOWN.ordinal(), ( Boolean ) input.keyState.get( Keyboard.Key.S ) );

        motion.velocity = new Vector2f( 0, 0 );

        if( abilities.get( GameObject.Ability.MOVE_LEFT.ordinal() ) == true )
        { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( -motion.speed, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_RIGHT.ordinal() ) == true )
        { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( motion.speed, 0 ) ); }

        if( abilities.get( GameObject.Ability.MOVE_UP.ordinal() ) == true )
        { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( 0, -motion.speed ) ); }

        if( abilities.get( GameObject.Ability.MOVE_DOWN.ordinal() ) == true )
        { motion.velocity = Vector2f.add( motion.velocity, new Vector2f( 0, motion.speed ) ); }

    }
}
