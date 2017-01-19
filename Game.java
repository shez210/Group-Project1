import org.jsfml.window.*;
import org.jsfml.graphics.*;

import javax.annotation.Resource;

public class Game
{
	private static final int SCREEN_WIDTH  = 640;
	private static final int SCREEN_HEIGHT = 480;

	private static final String TITLE = "Game";

	public static RenderWindow window;
	public static InputHandler inputHandler = new InputHandler();
	public static Resources resources = new Resources();


	public Game()
	{
		initWindow();
	}

	public static void initWindow()
	{
		window = new RenderWindow();
		window.create( new VideoMode( SCREEN_WIDTH, SCREEN_HEIGHT ), TITLE, WindowStyle.DEFAULT );
	}
}