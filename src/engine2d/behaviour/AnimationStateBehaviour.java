package engine2d.behaviour;

import engine2d.GameObject;

import java.util.ArrayList;

/** This class handles transitions between different animations.
 * You don't have to know how it works, just use it. */
public class AnimationStateBehaviour
{
    private ArrayList<AnimationBehaviour> anims;
    private ArrayList<Boolean> abilities;
    private AnimationBehaviour currentAnim;

    private int requestedAnim;
    private int animRecoilMillis = 30;

    public enum Type{ INTERRUPTABLE, WAIT_TO_FINISH }


    /** Attaches animation to a certain ability/state. Use it if you have more than one animation.
     *
     * @param anims All animations that are going to be used.
     * @param abilities All abilities that are going to be used.
     */
    public AnimationStateBehaviour( ArrayList<AnimationBehaviour> anims, ArrayList<Boolean> abilities )
    {
        this.anims = anims;
        this.abilities = abilities;
        currentAnim = anims.get( GameObject.Ability.IDLE.ordinal() );
    }

    public void update()
    {
        int i;
        for( i = 0; i < abilities.size(); i ++ ) { if( abilities.get( i ) == true ) { break; } }

        if( i < abilities.size() )
        {
            if( abilities.get( GameObject.Ability.MOVE_UP.ordinal() ) == true )
            { changeAnimationTo( GameObject.Ability.MOVE_UP.ordinal(), Type.INTERRUPTABLE.ordinal() ); }

            if( abilities.get( GameObject.Ability.MOVE_DOWN.ordinal() ) == true )
            { changeAnimationTo( GameObject.Ability.MOVE_UP.ordinal(), Type.INTERRUPTABLE.ordinal() ); }

            if( abilities.get( GameObject.Ability.MOVE_LEFT.ordinal() ) == true )
            { changeAnimationTo( GameObject.Ability.MOVE_UP.ordinal(), Type.INTERRUPTABLE.ordinal() ); }

            if( abilities.get( GameObject.Ability.MOVE_RIGHT.ordinal() ) == true )
            { changeAnimationTo( GameObject.Ability.MOVE_UP.ordinal(), Type.INTERRUPTABLE.ordinal() ); }

            if( abilities.get( GameObject.Ability.ATTACK.ordinal() ) == true )
            { changeAnimationTo( GameObject.Ability.ATTACK.ordinal(), Type.WAIT_TO_FINISH.ordinal() ); }
        }
        else
        {
            changeAnimationTo( GameObject.Ability.IDLE.ordinal(), Type.INTERRUPTABLE.ordinal() );
        }
        //else currentAnim.showOnlyFirstFrame();

        currentAnim.update();
    }

    public void changeAnimationTo( int anim, int flag )
    {
        requestedAnim = anim;
    }

    public void startNewAnimation( int anim )
    {
        currentAnim = anims.get( anim );
    }

    public boolean isEndOfCurrentAnimation()
    {
        return currentAnim.timer.getElapsedTime().asMilliseconds() > currentAnim.maxAnimationTimeMillis - animRecoilMillis;
    }
}
