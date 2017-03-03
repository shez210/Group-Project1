package engine2d;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/** Holds all game content (textures, sounds, music, fonts, etc). */
public class Resources
{
    public Font font = new Font();
    public Sprite cursorSprite;
    public ArrayList<Texture> knightIdle = new ArrayList<>();
    public ArrayList<Texture> knightRun = new ArrayList<>();
    public ArrayList<Texture> knightAttack = new ArrayList<>();
    public ArrayList<Texture> knightDead = new ArrayList<>();
    public HashMap<String, Vector2f> pointsOfOrigin = new HashMap<>();
    public HashMap<String, Texture> textures = new HashMap<>();
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


        loadTexturesFromFolder( "sprites/knight/Idle", knightIdle, new Color( 3, 2, 1 ) );
        loadTexturesFromFolder( "sprites/knight/Run", knightRun, new Color( 3, 2, 1 ) );
        loadTexturesFromFolder( "sprites/knight/Attack", knightAttack, new Color( 3, 2, 1 ) );
        loadTexturesFromFolder( "sprites/knight/Dead", knightDead, new Color( 3, 2, 1 ) );
        pointsOfOrigin.put( "knightIdle", new Vector2f( 36, 94 ) );
        pointsOfOrigin.put( "knightRun", new Vector2f( 44, 101 ) );
        pointsOfOrigin.put( "knightAttack", new Vector2f( 44, 99 ) );
        pointsOfOrigin.put( "knightDead", new Vector2f( 132, 99 ) );

        loadTexture( "sprites/chest_open.png", Color.WHITE );
        loadTexture( "sprites/cursor.png", Color.WHITE );
        loadTexture( "sprites/shadow.png", Color.WHITE );
        loadTexture( "sprites/blood_splash.png", Color.WHITE );

        loadTexture( "sprites/health_box.png", Color.WHITE );
        loadTexture( "sprites/health_bar_full.png", Color.WHITE );
        loadTexture( "sprites/health_bar_empty.png", Color.WHITE );
        // If you want to call loadTexture(), do it HERE, not before the other function calls.
        // load sprite sheet here.

        loadTexture("sprites/Images/Backgrounds/deathscroll.png", Color.WHITE );
        loadTexture("sprites/Images/Buttons/NewGame.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/NewGmeHover.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/Quit.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/QutHover.png", Color.WHITE);
        loadTexture("sprites/Images/Buttons/Title.png", Color.WHITE);
        loadTexture("sprites/Images/Backgrounds/newBg.png", Color.WHITE);

        loadTextureFromSpriteSheet("sprites/tiles.png", "greenFloor1", 2, 114, new Vector2i( 1, 0 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "greenFloor2", 2, 114, new Vector2i( 2, 0 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneFloor1", 2, 114, new Vector2i( 3, 3 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallNorth1", 2, 114, new Vector2i( 16, 2 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallSouth1", 2, 114, new Vector2i( 2, 16 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallWest1", 2, 114, new Vector2i( 7, 2 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallEast1", 2, 114, new Vector2i( 2, 10 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallNorthWest1", 2, 114, new Vector2i( 4, 2 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallNorthEast1", 2, 114, new Vector2i( 3, 2 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallSouthWest1", 2, 114, new Vector2i( 6, 2 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallSouthEast1", 2, 114, new Vector2i( 5, 2 ) );

        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallNorthWestInverted1", 2, 114, new Vector2i( 2, 14 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallNorthEastInverted1", 2, 114, new Vector2i( 2, 15 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallSouthWestInverted1", 2, 114, new Vector2i( 2, 11 ) );
        loadTextureFromSpriteSheet("sprites/tiles.png", "stoneWallSouthEastInverted1", 2, 114, new Vector2i( 2, 12 ) );

        loadFont( "font.ttf" );

        loadSound( "Music/repentant.wav" );
        loadSound( "Music/Projectile.wav" );
        loadSound( "Music/menu.wav" );

        //Set the cursor stuff.
        cursorSprite = new Sprite( textures.get( "cursor" ) );
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
        textures.put( Utility.getNameOnly( path ), tex );
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

    public void loadTextureFromSpriteSheet( String path, String name, int offset, int tileSize, Vector2i pos )
    {
        Texture tex = new Texture();
        int x = ( pos.x + 1 )*offset + pos.x*tileSize;
        int y = ( pos.y + 1 )*offset + pos.y*tileSize;
        try{ tex.loadFromFile( Paths.get( path ), new IntRect( x, y, tileSize, tileSize ) ); }
        catch ( IOException e ) { e.printStackTrace(); }
        tex.setSmooth( true );
        textures.put( name, tex );
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
