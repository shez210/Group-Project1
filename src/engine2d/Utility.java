package engine2d;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;


public class Utility
{
    public static List<String> readMapFromFile( String path )
    {
        List<String> lines = null;
        try{ lines = Files.readAllLines( Paths.get( path ), Charset.forName( "UTF-8" ) ); }
        catch( IOException e ) { e.printStackTrace(); }

        for( Iterator<String> i = lines.iterator(); i.hasNext();)
        {
            String line = i.next();
            if( line.isEmpty() == false && Character.isDigit( line.charAt( 0 ) ) == false ) { i.remove(); }
        }

        return lines;
    }

    public static String getNameOnly( String path )
    {
        String string = new File( path ).getName();
        return string.substring( 0, string.lastIndexOf( '.' ) );
    }

    public static void turnLeft( Sprite sprite )
    {
        sprite.setScale( Vector2f.componentwiseMul( sprite.getScale(), new Vector2f( -1.0f, 1.0f ) ) );
        sprite.setPosition( Vector2f.add( sprite.getPosition(), new Vector2f( sprite.getGlobalBounds().width/2, 0 ) ) );
    }

    public static void turnRight( Sprite sprite )
    {
        sprite.setScale( Vector2f.componentwiseMul( sprite.getScale(), new Vector2f( -1.0f, 1.0f ) ) );
        sprite.setPosition( Vector2f.add( sprite.getPosition(), new Vector2f( -sprite.getGlobalBounds().width/2, 0 ) ) );
    }
}
