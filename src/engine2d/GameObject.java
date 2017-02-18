package engine2d;

import engine2d.behaviour.*;
import org.jsfml.graphics.IntRect;
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
    public Sprite shadow;
    public ColliderBehaviour collider;
    public AIBehaviour ai;
    public ArrayList<AnimationBehaviour> anims;
    public StatsBehaviour stats;
    public StateBehaviour state;
    public AtomicInteger health = new AtomicInteger( 100 );
    public boolean collidable = false;

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
        if( collider != null ) { collider.update(); } // If the engine2d.GameObject can move, then move it.
        if( state != null ) { state.update(); } // If the engine2d.GameObject supports animation, update it.
        if( ai != null ) { ai.update(); }
        if( shadow != null )
        {
            if( Vec2f.greaterThan( sprite.getScale(), new Vector2f( 0, 0 ) ) )
                shadow.setPosition( new Vector2f( sprite.getGlobalBounds().left + state.currentAnim.originPoint.x, sprite.getGlobalBounds().top + state.currentAnim.originPoint.y + 30 ) );

            else
                shadow.setPosition( new Vector2f( sprite.getGlobalBounds().left + sprite.getGlobalBounds().width - state.currentAnim.originPoint.x, sprite.getGlobalBounds().top + state.currentAnim.originPoint.y + 30 ) );
        }
    }

    boolean hasMotion()
    {
        return collider != null;
    }

    void addBehaviour( AnimationBehaviour anim, int abilityIndex )
    {
        anims.set( abilityIndex, anim );
    }

    void addBehaviour( StateBehaviour state )
    {
        this.state = state;
    }

    void addBehaviour( ColliderBehaviour collider )
    {
        this.collider = collider;
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
