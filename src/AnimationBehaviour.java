import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;


// Animates an object when it's added to it.
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

    /**
     * Automatically animates the sprite based on the spritesheet provided.
     * @param spr the sprite that is going to be animated.
     * @param numSequencesRow the number of sprites per row in the sprite texture.
     *                        for example there are 15 sprites per row in this texture here: http://www.gamefromscratch.com/image.axd?picture=a.png
     * @param totalSequences the total number of sprites in the spritesheet/texture. in the link above, there are total of 32 sprites.
     */
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

    // Sets the animation speed.
    public void setAnimationSpeed( float factor )
    {
        millisPerSequence = ( int ) ( ( float ) SEQUENCE_SPEED_NORMAL/factor );
    }

    // Updates the animation. Should be called every frame.
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
