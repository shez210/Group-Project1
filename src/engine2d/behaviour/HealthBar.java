package engine2d.behaviour;

import engine2d.App;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.io.IOException;
import java.nio.file.Paths;

class HealthBar extends App
{
    /**
     * Create the image by loading the texture from the provided String and set the initial X and Y location
     *
     * @param  the ScreenInformation containing details about the screen
     * @param textureFile       the String filename to load
     */
    public HealthBar(int x, int y, String textureFile)
    {
        //Load image/texture
        Vector2i size = new Vector2i(1, 1);
        Texture texture = new Texture();
        try
        {
            texture.loadFromFile(Paths.get(textureFile));
            size = texture.getSize();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        texture.setSmooth(true);

        SpriteWrapper img = new SpriteWrapper(texture);
        img.setTextureRect(new IntRect(0, 0, 241, 58));
        img.setOrigin(0, 0);

        this.SCREEN_HEIGHT = x;
        this.SCREEN_WIDTH = y;
    }
}