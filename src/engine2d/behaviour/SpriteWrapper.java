package engine2d.behaviour;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Jonnys on 21/02/2016.
 */
public class SpriteWrapper extends Sprite implements Serializable
{

    public SpriteWrapper()
    {
        super();
    }

    public SpriteWrapper(ConstTexture texture)
    {
        super(texture);
    }

    public SpriteWrapper(ConstTexture texture, IntRect rect)
    {
        super(texture, rect);
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(super.getColor());
        out.writeObject(super.getOrigin());
        out.writeObject(super.getPosition());
        out.writeObject(super.getRotation());
        out.writeObject(super.getScale());
        out.writeObject(super.getTextureRect());
    }

    private void readObject(ObjectInputStream in) throws IOException
    {

        try
        {
            in.defaultReadObject();
            super.setColor((Color) in.readObject());
            super.setOrigin((Vector2f) in.readObject());
            super.setPosition((Vector2f) in.readObject());
            super.setRotation((Float) in.readObject());
            super.setScale((Vector2f) in.readObject());
            super.setTextureRect((IntRect) in.readObject());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
