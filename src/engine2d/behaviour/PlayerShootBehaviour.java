package engine2d.behaviour;

import engine2d.InputHandler;
import org.jsfml.audio.*;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;
import java.nio.file.Paths;

/** its an overkill, this class. but makes the code more readable. */
public class PlayerShootBehaviour
{
    private InputHandler input;
    private Sprite playerSprite;
    private Clock launchCooldown; // firing cooldown.
    private boolean isProjectileLaunched;
    public Sound shootMusic;

    public PlayerShootBehaviour( InputHandler input, Sprite playerSprite )
    {
        this.input = input;
        this.playerSprite = playerSprite;
    }

    public void update()
    {

        if( input.isMouseClicked == true && launchCooldown.getElapsedTime().asSeconds() > 0.1f )
        {
            shootMusic.play();
            isProjectileLaunched = true;
            launchCooldown.restart();
            shootMusic.stop();
        }
        else isProjectileLaunched = false;
    }

    public boolean isProjectileLaunched()
    {
        return isProjectileLaunched;
    }
}
