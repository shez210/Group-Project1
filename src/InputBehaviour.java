import java.util.ArrayList;

import static org.jsfml.window.Keyboard.Key;

public class InputBehaviour
{
    private InputHandler input;
    private ArrayList<Boolean> abilities;

    public InputBehaviour( InputHandler input, ArrayList<Boolean> abilities )
    {
        this.input = input;
        this.abilities = abilities;
    }

    public void update()
    {
        abilities.set( GameObject.Ability.MOVE_LEFT.ordinal(), ( Boolean ) input.keyState.get( Key.A ) );
        abilities.set( GameObject.Ability.MOVE_RIGHT.ordinal(), ( Boolean ) input.keyState.get( Key.D ) );
        abilities.set( GameObject.Ability.MOVE_UP.ordinal(), ( Boolean ) input.keyState.get( Key.W ) );
        abilities.set( GameObject.Ability.MOVE_DOWN.ordinal(), ( Boolean ) input.keyState.get( Key.S ) );
    }
}
