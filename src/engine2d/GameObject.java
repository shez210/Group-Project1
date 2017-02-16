package engine2d;

import engine2d.behaviour.*;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/** Try not to touch */
public class GameObject
{
    public Type type;
    public Status status = Status.ACTIVE;
    public Sprite sprite;
    public MotionBehaviour motion;
    public AIBehaviour ai;
    public ArrayList<AnimationBehaviour> anims;
    public StatsBehaviour stats;
    public StateBehaviour state;
    public AtomicInteger health = new AtomicInteger( 100 );

    public enum Type{ PLAYER, PLAYER_BULLET, ENEMY }
    public enum Status{ ACTIVE, INACTIVE }

    // engine2d.GameObject is created by instantiating all the needed components/behaviours.
    public GameObject()
    {
        sprite = new Sprite();
        anims = new ArrayList<>( Collections.nCopies( StateBehaviour.State.values().length, null ) );
    }

    void update()
    {
        if( motion != null ) { motion.update(); } // If the engine2d.GameObject can move, then move it.
        if( state != null ) { state.update(); } // If the engine2d.GameObject supports animation, update it.
        if( ai != null ) { ai.update(); }
    }

    boolean hasMotion()
    {
        return motion != null;
    }

    void addBehaviour( AnimationBehaviour anim, int abilityIndex )
    {
        anims.set( abilityIndex, anim );
    }

    void addBehaviour( StateBehaviour state )
    {
        this.state = state;
    }

    void addBehaviour( MotionBehaviour motion )
    {
        this.motion = motion;
    }

    void addBehaviour( AIBehaviour ai )
    {
        this.ai = ai;
    }

    void addBehaviour( StatsBehaviour stats )
    {
        this.stats = stats;
    }

    void addTexture( Texture texture )
    {
        sprite.setTexture( texture );
    }
}
