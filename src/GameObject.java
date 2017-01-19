import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

//author
public class GameObject
{
    public Sprite sprite;
    public AnimationBehaviour anim;
    public InputBehaviour input;
    public Vector2f velocity;


    // GameObject is created by instantiating all the needed components/behaviours.
    public GameObject( Texture tex )
    {
        sprite = new Sprite( tex ); // Assigning a texture to sprite.
        anim = new AnimationBehaviour( sprite, 7, 27 ); // Add animation behaviour to game object.
        velocity = new Vector2f( 0, 0 );
        input = new InputBehaviour( Game.inputHandler, this ); // Add input behaviour to game object.
        sprite.setScale( 0.5f, 0.5f );
    }

    public void setVelocity( Vector2f velocity )
    {
        this.velocity = velocity;
    }
    public Vector2f getVelocity()
    {
        return velocity;
    }

    void update()
    {
        if( anim != null ) { anim.update(); } // If the GameObject supports animation, update it.
        if( input != null ) { input.update(); } // If the GameObject is controlled by input, handle the input.

        sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) ); // moves the sprite.
    }
}
