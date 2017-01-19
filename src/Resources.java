import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

//author


public class Resources
{
    public int counter = 0;
    public ArrayList<Texture> textures = new ArrayList<>();

    // Precaches resources (only sprites for now).
    public Resources()
    {
        //loadSpriteSheet( "dude_running.png" );
        loadSpriteSheet( "dude_running.png" );
    }

    /** Loads sprite sheet
     *
     * @param path the relative path to the file.
     */
    public void loadSpriteSheet( String path )
    {
        textures.add( new Texture() );
        Texture lastTex = textures.get( textures.size() - 1 );
        try{ lastTex.loadFromFile( Paths.get( path ) ); }
        catch( IOException ex ) { ex.printStackTrace(); }
    }
}
