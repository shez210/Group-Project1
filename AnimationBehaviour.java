import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

public class AnimationBehaviour
{
    private Clock timer;
    private Sprite sprite;
    private Texture texture;

    private final int SEQUENCE_SPEED_NORMAL = 80;
    private int texWidth;
    private int texHeight;
    private int animSequences;
    private int currentSequence = 0;
    private int millisPerSequence = SEQUENCE_SPEED_NORMAL;
    private int numSequencesRow;
    private int numSequencesColumn;

    public AnimationBehaviour( Sprite spr, int numSequencesRow, int totalSequences )
    {
        timer = new Clock();
        sprite = spr;
        texture = ( Texture ) sprite.getTexture();
        texWidth  = texture.copyToImage().toBufferedImage().getWidth();
        texHeight = texture.copyToImage().toBufferedImage().getHeight();
        this.numSequencesRow = numSequencesRow;
        this.numSequencesColumn = ( totalSequences%numSequencesRow == 0 ) ?
                                    totalSequences/numSequencesRow : totalSequences/numSequencesRow + 1;
        animSequences = totalSequences;

    }

    public void setAnimationSpeed( float factor )
    {
        millisPerSequence = ( int ) ( ( float ) SEQUENCE_SPEED_NORMAL/factor );
    }

    public void update()
    {
        if( timer.getElapsedTime().asMilliseconds() > millisPerSequence )
        {
            currentSequence ++;
            if( currentSequence == animSequences ) { currentSequence = 0; }
            timer.restart();
            int rectX = ( currentSequence%numSequencesRow )*texWidth/numSequencesRow;
            int rectY = ( currentSequence/numSequencesRow )*texHeight/numSequencesColumn;
            int rectW = texWidth/numSequencesRow;
            int rectH = texHeight/numSequencesColumn;
            sprite.setTextureRect( new IntRect( rectX, rectY, rectW, rectH ) );
            //System.out.printf( "x = %d, y = %d\n", rectX, rectY );
            //sprite.setPosition( 100, 100 );
        }
    }
}
