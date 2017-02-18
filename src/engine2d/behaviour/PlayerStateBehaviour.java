
package engine2d.behaviour;

import engine2d.App;
import engine2d.GameObject;
import engine2d.InputHandler;
import engine2d.Utility;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

public class PlayerStateBehaviour extends StateBehaviour
{
    InputHandler input;
    GameObject object;
    Clock debug = new Clock();

    public PlayerStateBehaviour( InputHandler input, GameObject object )
    {
        super( object.sprite, object.collider, object.anims );
        this.input = input;
        this.object = object;
    }

    public void handleInput()
    {
        // Handle state switch.
        switch( currentState )
        {
            case IDLE:
                if( ( boolean )input.keyState.get( Keyboard.Key.A ) || ( boolean )input.keyState.get( Keyboard.Key.D )
                ||  ( boolean )input.keyState.get( Keyboard.Key.W ) || ( boolean )input.keyState.get( Keyboard.Key.S ) )
                {
                    currentState = StateBehaviour.State.MOVE;
                }
                if( ( boolean )input.keyState.get( Keyboard.Key.SPACE ) )
                {
                    currentState = StateBehaviour.State.ATTACK;
                }
                break;

            case MOVE:
                if( input.keyState.containsValue( true ) == false )
                {
                    currentState = StateBehaviour.State.IDLE;
                }
                if( ( boolean )input.keyState.get( Keyboard.Key.SPACE ) )
                {
                    //App.resources.getSound( "Projectile" ).play();
                    currentState = StateBehaviour.State.ATTACK;
                }
                break;
            case ATTACK:
                if( currentAnim.isEnding() )
                {
                    if( ( boolean )input.keyState.get( Keyboard.Key.A ) || ( boolean )input.keyState.get( Keyboard.Key.D )
                    ||  ( boolean )input.keyState.get( Keyboard.Key.W ) || ( boolean )input.keyState.get( Keyboard.Key.S ) )
                    {
                        currentState = StateBehaviour.State.MOVE;
                    }

                    else if( input.keyState.containsValue( true ) == false )
                    {
                        currentState = StateBehaviour.State.IDLE;
                    }
                }
                break;
        }

        if( ( boolean ) App.inputHandler.keyState.get( Keyboard.Key.R ) && debug.getElapsedTime().asSeconds() > 0.2f )
        {
            debug.restart();
            if( currentState.ordinal() == State.values().length - 1 )
            {
                currentState = State.IDLE;
            }

            else currentState = State.values()[ currentState.ordinal() + 1 ];
        }
        if( ( boolean ) App.inputHandler.keyState.get( Keyboard.Key.G ) )
        {
            currentAnim.setAnimationSpeed( 0.01f );
        }
        if( ( boolean ) App.inputHandler.keyState.get( Keyboard.Key.H ) )
        {
            currentAnim.setAnimationSpeed( 1.0f );
        }

        if( object.health.get() <= 0 ) { currentState = State.DEAD; }

        //Logic
        if( currentState == State.IDLE || currentState == State.ATTACK || currentState == State.DEAD )
        {
            collider.velocity = new Vector2f( 0, 0 );
        }

        if( currentState == State.MOVE )
        {
            collider.velocity = new Vector2f( 0, 0 );
            if( ( boolean )input.keyState.get( Keyboard.Key.A ) )
            {
                if( sprite.getScale().x > 0 ) { Utility.turnLeft( sprite ); }
                collider.velocity = Vector2f.add( collider.velocity, new Vector2f( -collider.speed, 0 ) );
            }

            if( ( boolean )input.keyState.get( Keyboard.Key.D ) )
            {
                if( sprite.getScale().x < 0 ) { Utility.turnRight( sprite ); }
                collider.velocity = Vector2f.add( collider.velocity, new Vector2f( collider.speed, 0 ) );
            }

            if( ( boolean )input.keyState.get( Keyboard.Key.W ) )
            { collider.velocity = Vector2f.add( collider.velocity, new Vector2f( 0, -collider.speed ) ); }

            if( ( boolean )input.keyState.get( Keyboard.Key.S ) )
            { collider.velocity = Vector2f.add( collider.velocity, new Vector2f( 0, collider.speed ) ); }
        }

        if( currentState == State.DEAD )
        {
            if( currentAnim.isEnding() )
            {
                object.status = GameObject.Status.INACTIVE;
            }
        }
    }
}
