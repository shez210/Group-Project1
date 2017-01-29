import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;

public class AIBehaviour
{
    MotionBehaviour motion;
    Sprite aiSprite;
    Sprite targetSprite;

    public AIBehaviour( MotionBehaviour motion, Sprite aiSprite, Sprite targetSprite )
    {
        this.motion = motion;
        this.aiSprite = aiSprite;
        this.targetSprite = targetSprite;
    }

    public void update()
    {
        chaseTarget();
    }

    public void chaseTarget()
    {
        Vector2f diff = Vector2f.sub( targetSprite.getPosition(), aiSprite.getPosition() );
        motion.velocity = Vector2f.div( diff, 130.0f );
    }
}
