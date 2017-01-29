
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;

/** its an overkill, this class. but makes the code more readable. */
public class PlayerShootBehaviour
{
    private InputHandler input;
    private Sprite playerSprite;
    private Clock launchCooldown; // firing cooldown.
    private boolean isProjectileLaunched;

    public PlayerShootBehaviour( InputHandler input, Sprite playerSprite )
    {
        this.input = input;
        this.playerSprite = playerSprite;
    }

    public void update()
    {
        if( input.isMouseClicked == true && launchCooldown.getElapsedTime().asSeconds() > 0.1f )
        {
            isProjectileLaunched = true;
            launchCooldown.restart();
        }
        else isProjectileLaunched = false;
    }
}
