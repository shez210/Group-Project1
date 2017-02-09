package engine2d;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Clock;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;

/** Handles window initialization, settings, holds all resources and records input. */
public class App
{
	public static final int SCREEN_WIDTH  = 615;
	public static final int SCREEN_HEIGHT = 480;
	private static final String TITLE = "Dungeon Crawler";

	public static RenderWindow window;
	public static InputHandler inputHandler = new InputHandler();
	public static Resources resources = new Resources();
	public static GameState currentState;
	public static Clock timer = new Clock();


	public App()
	{
		initWindow();
		currentState = new Menu();
	}

	public static void init()
	{
		new App();
	}

	public static void initWindow()
	{
		window = new RenderWindow();
		window.create( new VideoMode( SCREEN_WIDTH, SCREEN_HEIGHT ), TITLE, WindowStyle.DEFAULT );
		App.window.setFramerateLimit( 120 ); // Avoid excessive updates
		App.window.setMouseCursorVisible( false );
	}
}