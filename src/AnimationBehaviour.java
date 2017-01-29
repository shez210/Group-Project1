
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

// Guys, try not to touch this code because it's barely working. Just use it if you want animations.
// It's really easy to use.

// If someone finds a bug, PM me. @bogdann96

// Description is above the constructor.

/** Animates an object using a sprite sheet. */
public class AnimationBehaviour
{
    private Clock timer;
    private Sprite sprite;
    private Texture texture;

    private final int SEQUENCE_SPEED_NORMAL = 80; // Milliseconds spent on 1 sprite.
    private int texWidth; // Width of texture used.
    private int texHeight; // Height of texture.
    private int totalSequences; // Total sprites in the sprite sheet.
    private int currentSequence; // The sprite that is currently shown.
    private int sequenceStart; // The index of the first sprite that is going to be used from the sprite sheet.
    private int sequenceEnd; // The index of last sprite from sprite sheet.
    private int millisPerSequence = SEQUENCE_SPEED_NORMAL;
    private int numSequencesRow; // Number of sprites per row in the sprite sheet.
    private int numSequencesColumn; // Number of sprites per column in the sprite sheet.

    // Used for idle animations.
    private boolean showOnlyOneFrame; // Supports the option to show only the first frame of a certain animation.


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

    /** Sets the animation speed. */
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

    /** Updates the animation. Should be called every frame. */
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
