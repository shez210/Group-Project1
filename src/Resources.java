
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Resources
{
    public ArrayList<Texture> textures = new ArrayList<>();

    // Precache resources (only sprites for now).
    public Resources()
    {
        loadSpriteSheet( "player_running.png", new Color( 255, 254 ,254 ) );
        loadSpriteSheet( "placeholder_sprite.png", Color.WHITE );
        loadSpriteSheet( "chest_sprite.png", Color.WHITE );
    }

    /** Loads sprite sheet
     * @param path the relative path to the file.
     */
    public void loadSpriteSheet( String path, Color color )
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
}
