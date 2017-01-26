
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.util.ArrayList;
import java.util.Collections;

//author
public class GameObject
{
    public Sprite sprite;
    public InputBehaviour input;
    public MotionBehaviour motion;
    public ArrayList<Boolean> abilities;
    public ArrayList<AnimationBehaviour> anims;
    public AnimationStateBehaviour animState;

    public enum Ability{ MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN, MOVE_ATTACK }

    // GameObject is created by instantiating all the needed components/behaviours.
    public GameObject()
    {
        sprite = new Sprite();
        abilities = new ArrayList<>( Collections.nCopies( Ability.values().length, false ) );
        anims = new ArrayList<>( Collections.nCopies( Ability.values().length, null ) );
    }

    void update()
    {
        if ( input != null ) { input.update(); } // If the GameObject is controlled by input, handle the input.
        if ( motion != null ) { motion.update(); } // If the GameObject can move, then move it.
        if ( animState != null ) { animState.update(); } // If the GameObject supports animation, update it.
    }

    void addBehaviour( AnimationBehaviour anim, int abilityIndex )
    {
        this.anims.set( abilityIndex, anim );
    }

    void addBehaviour( AnimationBehaviour anim )
    {
        this.anims.add( anim );
    }

    void addBehaviour( AnimationStateBehaviour animState )
    {
        this.animState = animState;
    }

    void addBehaviour( InputBehaviour input )
    {
        this.input = input;
    }

    void addBehaviour( MotionBehaviour motion )
    {
        this.motion = motion;
    }

    void addTexture( Texture texture ) { this.sprite.setTexture( texture ); }
}
