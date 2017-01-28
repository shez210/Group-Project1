import org.jsfml.system.Vector2f;

// This class adds additional functionality to JSFML Vector2f.
public class Vec2f
{
    // Normalizes a 2D vector.
    public static Vector2f normalize( Vector2f vec )
    {
        if( length( vec ) == 0 ) System.out.println( "Division by 0 (in normalize()).");
        return Vector2f.div( vec, length( vec ) );
    }

    // Gets the length of a 2D vector.
    public static float length( Vector2f vec )
    {
        return ( float ) Math.sqrt( vec.x*vec.x + vec.y*vec.y );
    }

    // Gets the dot product of 2 vectors.
    public static float dot( Vector2f vec1, Vector2f vec2 )
    {
        return ( vec1.x*vec2.x + vec1.y*vec2.y );
    }
}
