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

    private int animRecoilMillis = 30;
    private int currentAnimIndex;

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
        for( i = 1; i < abilities.size(); i ++ ) // 'i' starts at IDLE + 1
        {
            if( abilities.get( i ) == true )
            {
                abilities.set( GameObject.Ability.IDLE.ordinal(), false );
                break;
            }
        }
        if( i == abilities.size() ) { abilities.set( GameObject.Ability.IDLE.ordinal(), true ); }

        if( abilities.get( GameObject.Ability.MOVE.ordinal() ) == true )
        { changeAnimationTo( GameObject.Ability.MOVE.ordinal(), Type.INTERRUPTABLE.ordinal() ); }

        if( abilities.get( GameObject.Ability.ATTACK.ordinal() ) == true )
        { changeAnimationTo( GameObject.Ability.ATTACK.ordinal(), Type.WAIT_TO_FINISH.ordinal() ); }

        if( abilities.get( GameObject.Ability.IDLE.ordinal() ) == true )
        { changeAnimationTo( GameObject.Ability.IDLE.ordinal(), Type.INTERRUPTABLE.ordinal() ); }

        //else currentAnim.showOnlyFirstFrame();

        System.out.println( currentAnimIndex );

        currentAnim.update();
    }

    public void changeAnimationTo( int requestedAnimType, int flag )
    {
        if( requestedAnimType != currentAnimIndex )
        {
            currentAnim.reset();
            currentAnimIndex = requestedAnimType;
            currentAnim = anims.get( requestedAnimType );
        }
    }

    public boolean isEndOfCurrentAnimation()
    {
        return currentAnim.timer.getElapsedTime().asMilliseconds() > currentAnim.maxAnimationTimeMillis - animRecoilMillis;
    }
}
