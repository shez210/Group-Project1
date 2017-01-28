import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.util.ArrayList;

public class ShootBehaviour
{
    private ArrayList<GameObject> projectiles;
    private InputHandler input;
    private Sprite playerSprite;

    public ShootBehaviour( InputHandler input, Sprite playerSprite, ArrayList<GameObject> projectiles )
    {
        this.input = input;
        this.playerSprite = playerSprite;
        this.projectiles = projectiles;
    }

    public void update()
    {

        /*
        if( input.isMouseClicked == true ) { isProjectileLaunched = true; }
        if( isProjectileLaunched == true )
        {
            projectileDirection = Vector2f.sub( new Vector2f( input.mouseCoords ), playerSprite.getPosition() );
            onProjectileTravel();
        }

        Vector2f distance = Vector2f.sub( launchedProjectile.sprite.getPosition(), playerSprite.getPosition() );
        if( Vec2f.length( distance ) > 200 ) { destroyLaunchedProjectile(); }
        */
    }
}
