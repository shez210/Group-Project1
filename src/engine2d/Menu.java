package engine2d;


import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

public class Menu extends GameState
{
    public Vector2f centerOfScreen = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 );
    public Vector2f newGamePos = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 );
    public Vector2f quitPos = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 + 100 );
    public Vector2f gameTitlePos = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT / 2 - 180 );


    public Menu()
    {
        App.resources.getSound( "menu" ).play();

        createDecoration( App.resources.props.get( "deathscroll" ), centerOfScreen );
        createDecoration( App.resources.props.get( "NewGame" ), centerOfScreen );
        createDecoration( App.resources.props.get( "Quit" ), quitPos );
        createDecoration( App.resources.props.get( "Title" ), gameTitlePos );
    }

    public void update()
    {
        if( ( boolean )App.inputHandler.keyState.get( Keyboard.Key.RETURN ) )
        {
            App.currentState = new World();
        }

        if( App.inputHandler.isMouseOver( gameObjects.get( 1 ).sprite ) )
        {
            gameObjects.get( 1 ).sprite.setTexture( App.resources.props.get( "NewGmeHover" ) );
            if( App.inputHandler.isMouseClicked )
            {
                App.resources.getSound( "menu" ).stop();
                //App.resources.getSound("menu").setLoop(true);
                App.currentState = new World();

            }
        }
        else
        {
            gameObjects.get( 1 ).sprite.setTexture( App.resources.props.get( "NewGame" ) );
        }

        if( App.inputHandler.isMouseOver( gameObjects.get( 2 ).sprite ) )
        {
            gameObjects.get( 2 ).sprite.setTexture( App.resources.props.get( "QutHover" ) );
            if( App.inputHandler.isMouseClicked )
            {
                App.window.close();
            }

        }
        else
        {
            gameObjects.get( 2 ).sprite.setTexture( App.resources.props.get( "Quit" ) );
        }

        if( ( boolean )App.inputHandler.keyState.get( Keyboard.Key.T ) && ( toggleDelayTimer.getElapsedTime().asSeconds() > TOGGLE_DELAY ) )
        {
            toggleDelayTimer.restart();
            App.currentState = new Tutorial();
        }

        //if(input.isMouseClicked){App.currentState = new World();}
        App.resources.cursorSprite.setPosition( new Vector2f( App.inputHandler.mouseCoords ) ); //Set cursor position.
    }

    public void draw()
    {
        App.window.clear();
        for( GameObject object : gameObjects ) { App.window.draw( object.sprite ); }

        App.window.draw( App.resources.cursorSprite );


        App.window.display();
    }
}
