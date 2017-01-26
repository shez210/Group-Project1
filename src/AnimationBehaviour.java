
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

// Guys, try not to touch this code because it's barely working. Just use it if you want animations.
// If you find a bug, PM me. @bogdann96

// Description is above the constructor.

// Animates an object when it's added to it.
public class AnimationBehaviour
{
    private Clock timer;
    private Sprite sprite;
    private Texture texture;

    private final int SEQUENCE_SPEED_NORMAL = 80;
    private int texWidth;
    private int texHeight;
    private int totalSequences;
    private int currentSequence;
    private int sequenceStart;
    private int sequenceEnd;
    private int millisPerSequence = SEQUENCE_SPEED_NORMAL;
    private int numSequencesRow;
    private int numSequencesColumn;

    private boolean showOnlyOneFrame;

    /**
     * Automatically animates the sprite based on the spritesheet provided.
     * @param spr the sprite that is going to be animated.
     * @param numSequencesRow the number of sprites per row in the sprite texture.
     *                        for example there are 15 sprites per row in this texture here: http://www.gamefromscratch.com/image.axd?picture=a.png
     * @param totalSequences the total number of sprites in the spritesheet/texture. in the link above, there are total of 32 sprites.
     */
    public AnimationBehaviour( Sprite spr, int sequenceStart, int sequenceEnd, int numSequencesRow, int totalSequences )
    {
        timer = new Clock();
        sprite = spr;
        texture = ( Texture ) sprite.getTexture();
        texWidth  = texture.copyToImage().toBufferedImage().getWidth();
        texHeight = texture.copyToImage().toBufferedImage().getHeight();
        this.numSequencesRow = numSequencesRow;
        this.numSequencesColumn = ( totalSequences%numSequencesRow == 0 ) ?
                                    totalSequences/numSequencesRow : totalSequences/numSequencesRow + 1;
        this.sequenceStart = sequenceStart;
        currentSequence = sequenceStart;
        this.sequenceEnd = sequenceEnd;
        this.totalSequences = totalSequences;
    }

    // Sets the animation speed.
    public void setAnimationSpeed( float factor )
    {
        millisPerSequence = ( int ) ( ( float ) SEQUENCE_SPEED_NORMAL/factor );
    }

    public void showOnlyFirstFrame()
    {
        showOnlyOneFrame = true;
    }
    public void showAllFrames()
    {
        showOnlyOneFrame = false;
    }

    // Updates the animation. Should be called every frame.
    public void update()
    {
        if( timer.getElapsedTime().asMilliseconds() > millisPerSequence )
        {
            currentSequence ++;
            if( currentSequence == sequenceEnd ) { currentSequence = sequenceStart; }
            if( showOnlyOneFrame == true ) { currentSequence = sequenceStart; }
            timer.restart();
            int rectX = ( currentSequence%numSequencesRow )*texWidth/numSequencesRow;
            int rectY = ( currentSequence/numSequencesRow )*texHeight/numSequencesColumn;
            int rectW = texWidth/numSequencesRow;
            int rectH = texHeight/numSequencesColumn;
            sprite.setTextureRect( new IntRect( rectX, rectY, rectW, rectH ) );
        }
    }
}
