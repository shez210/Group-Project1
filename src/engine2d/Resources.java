package engine2d;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/** Holds all game content (textures, sounds, music, fonts, etc). */
public class Resources
{
    public ArrayList<Texture> textures = new ArrayList<>();
    public Font font = new Font();
    public Sprite cursorSprite;
    public ArrayList<Texture> knightIdle = new ArrayList<>();
    public ArrayList<Texture> knightRun = new ArrayList<>();
    public ArrayList<Texture> knightAttack = new ArrayList<>();
    public ArrayList<Texture> knightDead = new ArrayList<>();
    private HashMap<String, Sound> sounds = new HashMap<>();


    // Precache resources (only sprites and fonts for now).
    public Resources()
    {
        /**
        * Every time loadTexture() is called, a new element is added into the textures array.
        * So if you want to get the texture that you loaded the 2nd time you called loadTexture(), then you
        * Need to use game.resources.texture.get( 1 ), to access the 2nd texture.
        * The 2nd parameter is the background color of the sprite sheet.
        * You need to be really precise when specifying it or background wont be blended. */


        loadTexturesFromFolder( "sprites/knight/idle", knightIdle, new Color( 3, 2, 1 ) );
        loadTexturesFromFolder( "sprites/knight/run", knightRun, new Color( 3, 2, 1 ) );
        loadTexturesFromFolder( "sprites/knight/attack", knightAttack, new Color( 3, 2, 1 ) );
        loadTexturesFromFolder( "sprites/knight/dead", knightDead, new Color( 3, 2, 1 ) );

        loadTexture( "sprites/player_running.png", new Color( 255, 254 ,254 ) );
        loadTexture( "sprites/placeholder_sprite.png", Color.WHITE ); // Color.WHITE is 255 255 255
        loadTexture( "sprites/chest_sprite.png", Color.WHITE );
        loadTexture( "sprites/bullet.png", Color.WHITE );
        loadTexture( "sprites/cursor.png", Color.WHITE );
        loadTexture( "sprites/placeholder_tile.png", Color.WHITE );
        // If you want to call loadTexture(), do it HERE, not before the other function calls.
        // load sprite sheet here.

        loadTexture("sprites/Images/Backgrounds/deathscroll.png", Color.WHITE );
        loadTexture("sprites/Images/Buttons/NewGame.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/NewGmeHover.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/Quit.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/QutHover.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/Title.png", Color.WHITE);
        loadTexture("sprites/Images/Icons/health.png", Color.WHITE);
        loadTexture("sprites/Images/Backgrounds/newBg.png", Color.WHITE);
        loadTexture("sprites/Images/Tiles/passable/6.png", Color.WHITE);

        loadFont( "font.ttf" );

        loadSound( "Music/repentant.wav" );
        loadSound( "Music/Projectile.wav" );

        //Set the cursor stuff.
        cursorSprite = new Sprite( textures.get( 4 ) );
        cursorSprite.setScale( 0.47f, 0.47f );
    }

    /** Loads sprite sheet
     * @param path the relative path to the file.
      TODO: 2 constructors, one without color */
    public void loadTexture( String path, Color color )
    {
        Image img = new Image();
        try{ img.loadFromFile( Paths.get( path ) ); }
        catch ( IOException e ) { e.printStackTrace(); }
        img.createMaskFromColor( color, 0 );
        Texture tex = new Texture();
        try { tex.loadFromImage( img ); }
        catch ( TextureCreationException e )
        { e.printStackTrace(); }
        textures.add( tex );
    }

    public void loadTexture( String path, ArrayList<Texture> textures, Color color )
    {
        Image img = new Image();
        try{ img.loadFromFile( Paths.get( path ) ); }
        catch ( IOException e ) { e.printStackTrace(); }
        img.createMaskFromColor( color, 0 );
        Texture tex = new Texture();
        try { tex.loadFromImage( img ); }
        catch ( TextureCreationException e )
        { e.printStackTrace(); }
        tex.setSmooth( true );
        textures.add( tex );
    }

    public void loadTexturesFromFolder( String folderName, ArrayList<Texture> textures, Color color )
    {
        try
        {
            File folder = new File( folderName );
            File[] filesList = folder.listFiles();
            Arrays.sort( filesList );

            for( int i = 0; i < filesList.length; i++ )
            {
                loadTexture( filesList[ i ].getPath(), textures, color );
            }
        }
        catch( Exception e ) { e.printStackTrace(); }
    }

    /** Loads a font at the specified path.
     * @param path the path to the font.
     */
    public void loadFont( String path )
    {
        try{ font.loadFromFile( Paths.get( path ) ); }
        catch( IOException e ) { e.printStackTrace(); }
    }

    public void loadSound( String path )
    {
        // Load the file in a sound buffer.
        SoundBuffer buffer = new SoundBuffer();
        try{ buffer.loadFromFile( Paths.get( path ) ); }
        catch( IOException ex ) { ex.printStackTrace(); }

        //Create a sound from sound buffer and store it in a hashmap.
        Sound sound = new Sound( buffer );
        //sound.setLoop( true );
        sounds.put( Utility.getNameOnly( path ), sound );
    }

    public Sound getSound( String name )
    {
        return sounds.get( name );
    }
}
