import jdk.internal.util.xml.impl.Input;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;
import java.util.Collections;

//author
public class GameObject
{
    public Sprite sprite;
    public AnimationBehaviour anim;
    public InputBehaviour input;
    public MotionBehaviour motion;
    public ArrayList<Boolean> abilities;

    public enum Ability{ MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN }

    // GameObject is created by instantiating all the needed components/behaviours.
    public GameObject( Texture tex )
    {
        abilities = new ArrayList<>( Collections.nCopies( Ability.values().length, false ) );
        sprite = new Sprite( tex ); // Assigning a texture to sprite.
        sprite.setScale( 0.5f, 0.5f );


        /*
        anim = new AnimationBehaviour( sprite, 7, 27 ); // Add animation behaviour to game object.
        input = new InputBehaviour( Game.inputHandler, abilities ); // Add input behaviour to game object.
        motion = new MotionBehaviour( sprite, abilities );
        this.anim = anim; // Add animation behaviour to game object.
        this.input = input; // Add input behaviour to game object.
        this.motion = motion;
         */
    }

    void update()
    {
        if( anim != null ) { anim.update(); } // If the GameObject supports animation, update it.
        if( input != null ) { input.update(); } // If the GameObject is controlled by input, handle the input.
        if( motion != null ) { motion.update(); } // If the GameObject can move, then move it.
    }

    void addBehaviour( AnimationBehaviour anim )
    {
        this.anim = anim;
    }
    void addBehaviour( InputBehaviour input )
    {
        this.input = input;
    }
    void addBehaviour( MotionBehaviour motion )
    {
        this.motion = motion;
    }
}
