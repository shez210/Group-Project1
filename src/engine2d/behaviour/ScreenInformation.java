package engine2d.behaviour;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;
import java.nio.file.Paths;

public class ScreenInformation implements java.io.Serializable
{
    private int screenWidth;
    private int screenHeight;
    private int playerXPos;
    private int playerYPos;
    private boolean gameState = false;
    private boolean optionsState = false;
    private boolean menuState = true;
    private boolean deathState = false;
    private boolean pauseState = false;
    private boolean muted;
    private boolean startNewGame = true;
    private transient Sound menuMusic;
    private Character player = null;

    public ScreenInformation(int screenWidth, int screenHeight)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        SoundBuffer backgroundMusic = new SoundBuffer();
        try
        {
            backgroundMusic.loadFromFile(Paths.get("Music/menu-final.wav"));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        //Create a sound and set its buffer
        menuMusic = new Sound();
        menuMusic.setBuffer(backgroundMusic);
        menuMusic.setLoop(true);
    }

    /*
    *return screen width
    */
    public int getScreenWidth()
    {
        return screenWidth;
    }

    /*
      *set screen width
      */
    public void setScreenWidth(int screenWidth)
    {
        this.screenWidth = screenWidth;
    }

    /*
    *return screen height
    */
    public int getScreenHeight()
    {
        return screenHeight;
    }

    /*
    *set screen height
    */
    public void setScreenHeight(int screenHeight)
    {
        this.screenHeight = screenHeight;
    }
    /*
    *return the X location of player
    */
    public int getPlayerX()  //Added player tracking to allow enemies to know where the character is
    {
        return playerXPos;
    }

    /*
    *return the Y location of player
    */
    public int getPlayerY()
    {
        return playerYPos;
    }
    /*
    *set the Y location of player
    */
    public void setPlayerPos(int x, int y)
    {
        this.playerXPos = x;
        this.playerYPos = y;
    }

    public boolean isGameState()
    {
        return gameState;
    }
    /*
    *Set whether the game runs by Escape key
    */
    public void setGameState(boolean gameState)
    {
        this.gameState = gameState;
    }

    /*
    *return the boolean options
    */
    public boolean isOptionsState()
    {
        return optionsState;
    }

    /*
    *set boolean fo options
    */
    public void setOptionsState(boolean optionsState)
    {
        this.optionsState = optionsState;
    }
    /*
    *return the boolean of menu
    */
    public boolean isMenuState()
    {
        return menuState;
    }
    /*
    *set boolean for  munu
    */
    public void setMenuState(boolean menuState)
    {
        this.menuState = menuState;
    }

    /*
    *return the boolean that indicates if the game is mute
    */
    public boolean isMuted()
    {
        return muted;
    }
    /*
    *set the  whether mute is on or off
    */
    public void setMuted(boolean muted)
    {
        this.muted = muted;
    }

    public Sound getMenuMusic()
    {
        return menuMusic;
    }

    public void setMenuMusic(Sound menuMusic)
    {
        this.menuMusic = menuMusic;
    }

    public boolean isDeathState()
    {
        return deathState;
    }

    public void setDeathState(boolean deathState)
    {
        this.deathState = deathState;
    }

    public boolean isPauseState()
    {
        return pauseState;
    }

    public void setPauseState(boolean pauseState)
    {
        this.pauseState = pauseState;
    }

    public Character getPlayer()
    {
        return player;
    }

    public void setPlayer(Character player)
    {
        this.player = player;
    }

    public boolean isStartNewGame()
    {
        return startNewGame;
    }

    public void setStartNewGame(boolean startNewGame)
    {
        this.startNewGame = startNewGame;
    }
}
