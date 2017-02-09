package engine2d.behaviour;

import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;

import java.io.IOException;
import java.nio.file.Paths;

public class Icon {
    public Icon(String buttonImage, int x, int y) {
        Vector2i size = new Vector2i(0, 0);
        Texture texture = new Texture();

        try {
            texture.loadFromFile(Paths.get(buttonImage));
            size = texture.getSize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}

