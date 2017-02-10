package engine2d;

import org.jsfml.graphics.Sprite;

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
}
