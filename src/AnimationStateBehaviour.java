
import java.util.ArrayList;

// This class handles transitions between different animations.
// You don't have to know how it works, just use it.
public class AnimationStateBehaviour
{
    private ArrayList<AnimationBehaviour> anims;
    private ArrayList<Boolean> abilities;
    private AnimationBehaviour currentAnim;

    public AnimationStateBehaviour( ArrayList<AnimationBehaviour> anims, ArrayList<Boolean> abilities )
    {
        this.anims = anims;
        this.abilities = abilities;
        currentAnim = anims.get( GameObject.Ability.MOVE_LEFT.ordinal() );
    }

    public void update()
    {
        int i;
        for( i = 0; i < abilities.size(); i ++ ) { if( abilities.get( i ) == true ) { break; } }

        if( i < abilities.size() )
        {
            if( abilities.get( GameObject.Ability.MOVE_UP.ordinal() ) == true )
            { currentAnim = anims.get( GameObject.Ability.MOVE_UP.ordinal() ); }

            if( abilities.get( GameObject.Ability.MOVE_DOWN.ordinal() ) == true )
            { currentAnim = anims.get( GameObject.Ability.MOVE_DOWN.ordinal() ); }

            if( abilities.get( GameObject.Ability.MOVE_LEFT.ordinal() ) == true )
            { currentAnim = anims.get( GameObject.Ability.MOVE_LEFT.ordinal() ); }

            if( abilities.get( GameObject.Ability.MOVE_RIGHT.ordinal() ) == true )
            { currentAnim = anims.get( GameObject.Ability.MOVE_RIGHT.ordinal() ); }

            if( abilities.get( GameObject.Ability.MOVE_ATTACK.ordinal() ) == true )
            { currentAnim = anims.get( GameObject.Ability.MOVE_ATTACK.ordinal() ); }

            currentAnim.showAllFrames();
        }
        else currentAnim.showOnlyFirstFrame();

        currentAnim.update();
    }
}
