package engine2d.behaviour;
import org.jsfml.graphics.*;

import java.io.IOException;
import java.nio.file.Paths;

public class Message
{
    private int fontSize;
    private int x;
    private int y;
    private int r;

    Font sansRegular = new Font();
    Text text;

    public Message(int x, int y, int r, String message, Color c, int fontSize)
    {
        this.fontSize = fontSize;

        try
        {
            sansRegular.loadFromFile(
                    Paths.get("Fonts/OpenSans-Bold.ttf"));
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        text = new Text(message, sansRegular, this.fontSize);
        text.setColor(c);

        FloatRect textBounds = text.getLocalBounds();
        // Find middle and set as origin/ reference point
        text.setOrigin(0, 0);

        this.x = x;
        this.y = y;
        this.r = r;

        //obj = text;
    }

    void setColour(Color c)
    {
        text.setColor(c);
    }

    /**
     * Set the X and Y position of the Actor instance called on
     */
    void performMove(float x, float y)
    {
        text.setPosition(x, y);
    }

    /**
     * Render the Actor instance on the provided Window
     *
     * @param w the RenderWindow to render the Actor onto
     */
    void draw(RenderWindow w)
    {
        w.draw(text);
    }

    /**
     * @return the FloatRect representing the hitbox of the Message instance called on
     */
    FloatRect getHitbox()
    {
        return text.getGlobalBounds();
    }

    void changeMessage(String newMessage)
    {
        text.setString(newMessage);
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}