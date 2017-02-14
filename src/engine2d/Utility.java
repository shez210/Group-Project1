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

    public static String renameBulk( String folderName, String ReplaceFrom, String ReplaceWith )
    {
        try
        {
            File folder = new File( folderName );
            File[] filesList = folder.listFiles();
            for( int i = 0; i < filesList.length; i++ )
            {
                String newName = ( filesList[ i ].toString().replaceAll( ReplaceFrom, ReplaceWith ) );
                filesList[ i ].renameTo( new File( newName ) );
            }
            return "Successfully renamed " + filesList.length + " files.";
        }
        catch( Exception e ) { return ( e.getMessage() ); }
    }

    public static String getNameOnly( String path )
    {
        String string = new File( path ).getName();
        return string.substring( 0, string.lastIndexOf( '.' ) );
    }

    public static void turnLeft( Sprite sprite )
    {
        sprite.setScale( -1.0f, 1.001f );
    }

    public static void turnRight( Sprite sprite )
    {
        sprite.setScale( 1.0f, 1.001f );
    }
}
