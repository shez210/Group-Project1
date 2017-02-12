package engine2d;

import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

/** Holds all game content (textures, sounds, music, fonts, etc). */
public class Resources
{
    public ArrayList<Texture> textures = new ArrayList<>();
    public Font font = new Font();
    public Sprite cursorSprite;
    public ArrayList<Texture> knightIdle = new ArrayList<>();
    public ArrayList<Texture> knightRun = new ArrayList<>();
    public ArrayList<Texture> knightAttack = new ArrayList<>();


    // Precache resources (only sprites and fonts for now).
    public Resources()
    {
        /**
        * Every time precacheTexture() is called, a new element is added into the textures array.
        * So if you want to get the texture that you loaded the 2nd time you called precacheTexture(), then you
        * Need to use game.resources.texture.get( 1 ), to access the 2nd texture.
        * The 2nd parameter is the background color of the sprite sheet.
        * You need to be really precise when specifying it or background wont be blended. */

        for( int i = 1; i <= 10; ++ i )
        {
            precacheTexture( "sprites/freeknight/png/Idle (" + i + ").png", knightIdle, Color.WHITE );
            precacheTexture( "sprites/freeknight/png/Run (" + i + ").png", knightRun, Color.WHITE );
            precacheTexture( "sprites/freeknight/png/Attack (" + i + ").png", knightAttack, Color.WHITE );
        }

        precacheTexture( "sprites/player_running.png", new Color( 255, 254 ,254 ) );
        precacheTexture( "sprites/placeholder_sprite.png", Color.WHITE ); // Color.WHITE is 255 255 255
        precacheTexture( "sprites/chest_sprite.png", Color.WHITE );
        precacheTexture( "sprites/bullet.png", Color.WHITE );
        precacheTexture( "sprites/cursor.png", Color.WHITE );
        precacheTexture( "sprites/placeholder_tile.png", Color.WHITE );
        // If you want to call precacheTexture(), do it HERE, not before the other function calls.
        // load sprite sheet here.

        precacheTexture("sprites/Images/Backgrounds/deathscroll.png" );
        precacheTexture("sprites/Images/Buttons/NewGame.png");
        precacheTexture("sprites/Images/Buttons/NewGmeHover.png");
        precacheTexture("sprites/Images/Buttons/Quit.png");
        precacheTexture("sprites/Images/Buttons/QutHover.png");
        precacheTexture("sprites/Images/Buttons/Title.png");
        precacheTexture("sprites/Images/Icons/health.png");
        //precacheTexture("sprites/Images/Backgrounds/newBg.png");
        precacheTexture("sprites/Images/Tiles/passable/6.png");

        loadFont( "font.ttf" );

        //Set the cursor stuff.
        cursorSprite = new Sprite( textures.get( 4 ) );
        cursorSprite.setScale( 0.47f, 0.47f );
    }

    /** Loads sprite sheet
     * @param path the relative path to the file.
      TODO: 2 constructors, one without color */
    public void precacheTexture( String path, Color color )
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

    public void precacheTexture( String path, ArrayList<Texture> textures, Color color )
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

    /** Loads sprite sheet
     * @param path the relative path to the file.
     */
    public void precacheTexture( String path)
    {
        Image img = new Image();
        try{ img.loadFromFile( Paths.get( path ) ); }
        catch ( IOException e ) { e.printStackTrace(); }
        Texture tex = new Texture();
        try { tex.loadFromImage( img ); }
        catch ( TextureCreationException e )
        { e.printStackTrace(); }
        textures.add( tex );
    }


    /** Loads a font at the specified path.
     * @param path the path to the font.
     */
    public void loadFont( String path )
    {
        try{ font.loadFromFile( Paths.get( path ) ); }
        catch( IOException e ) { e.printStackTrace(); }
    }

}
