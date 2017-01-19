import org.jsfml.graphics.RenderWindow;
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

    public GameObject( Texture tex )
    {
        sprite = new Sprite( tex );
        anim = new AnimationBehaviour( sprite, 7, 27 );
        input = new InputBehaviour( Game.inputHandler, this );
        sprite.setScale( 0.5f, 0.5f );
        velocity = new Vector2f( 0, 0 );
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
        if( anim != null ) { anim.update(); }
        if( input != null ) { input.update(); }

        sprite.setPosition( Vector2f.add( sprite.getPosition(), velocity ) );
    }
}
