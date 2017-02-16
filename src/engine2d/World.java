package engine2d;

import engine2d.behaviour.*;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

import java.util.List;
import java.util.Random;

import static engine2d.Utility.readMapFromFile;

/** The engine2d.World class holds all game objects and establishes communication among them.
 * Handles creation and destruction of entities. */
public class World extends GameState
{
    public Clock timer = new Clock(); // doesnt belong here
    public Clock timerShoot = new Clock(); // doesnt belong here
    public int playerIndex = - 1; // index of the player object in the array of game objects.
    public Vector2f healthPos = new Vector2f( App.SCREEN_WIDTH / 2+295, App.SCREEN_HEIGHT/2-275 );
    public Vector2f healthPos1 = new Vector2f( App.SCREEN_WIDTH / 2+360, App.SCREEN_HEIGHT/2-275 );
    public Vector2f healthPos2 = new Vector2f( App.SCREEN_WIDTH / 2+230, App.SCREEN_HEIGHT/2-275 );
    public Vector2f backGround = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT/2 );
    public Vector2f door = new Vector2f( App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT/2 );


    public World()
    {
        App.resources.getSound( "repentant" ).play();
        //createDecoration( App.resources.textures.get( 13 ), backGround );
        //createDecoration( App.resources.textures.get( 12 ), healthPos );
        //createDecoration( App.resources.textures.get( 12 ), healthPos1 );
        //createDecoration( App.resources.textures.get( 12 ), healthPos2 );
        //createDecoration( App.resources.textures.get( 14 ), door );


        /** If you want to create something, just call some "create" function.
         * createDecoration() is for walls, tiles and map stuff. */
        //buildLevel();
        //createPlayer();
        //createPlayerBeta();
        createPlayerKnight();
        createEnemy();
        //addHealth();
        //createEnemyRandom();
        //createDecoration( App.resources.knightIdle.get( 1 ), new Vector2f( 50, 50 ) );
    }


    /** Handles creation and destruction of entities and also resolves collision. */
    public void update()
    {
        /*
        if( App.inputHandler.isMouseClicked == true && timerShoot.getElapsedTime().asSeconds() > 0.1f )
        {
            App.resources.getSound( "Projectile" ).play();
            timerShoot.restart();
            //createProjectile( App.resources.textures.get( 3 ), gameObjects.get( playerIndex ).sprite.getPosition(), gameObjects.get( playerIndex ).motion.direction );
        }
        */
        //if( timer.getElapsedTime().asSeconds() > 0.2f ) { createEnemyRandom(); timer.restart(); }


        for( GameObject object : gameObjects ) { object.update(); }
        resolveCollisions();
        resolveKills();
        checkForDeallocations();
        App.resources.cursorSprite.setPosition( new Vector2f( App.inputHandler.mouseCoords ) ); //Set cursor position.

        //Transition
        if( ( boolean )App.inputHandler.keyState.get( Keyboard.Key.M ) && ( toggleDelayTimer.getElapsedTime().asSeconds() > TOGGLE_DELAY ) )
        {
            toggleDelayTimer.restart();
            App.currentState = new Menu();
        }
    }

    /** Draws all created game objects on screen. */
    public void draw()
    {
        App.window.clear();

        for( GameObject object : gameObjects ) { App.window.draw( object.sprite ); }
        App.window.draw( new Text( "Use WASD to move and Space to attack.", App.resources.font, 30 ) );
        App.window.draw( new Text( "\nhealth = " + gameObjects.get( playerIndex ).health, App.resources.font, 30 ) );
        if( gameObjects.size() - 1 >= playerIndex + 1 )
        {
            App.window.draw( new Text( "\n                             enemy health = " + gameObjects.get( playerIndex + 1 ).health, App.resources.font, 30 ) );
        }

        App.window.draw( App.resources.cursorSprite );
        App.window.display();
    }

    /** Resolves all collisions that happen. Still not finished. Works only to kill enemies. */
    public void resolveCollisions()
    {
        for( int i = gameObjects.size() - 1; i >= 0; i -- )
        {
            for( int j = gameObjects.size() - 1; j >= 0; j -- )
            {


                /*
                if( i != j && gameObjects.get( i ).type == GameObject.Type.PLAYER_BULLET.ordinal()
                       && gameObjects.get( j ).type == GameObject.Type.ENEMY.ordinal()
                       && isCollision( gameObjects.get( i ).sprite, gameObjects.get( j ).sprite ) )
                {
                    gameObjects.get( j ).status = GameObject.Status.DEAD.ordinal();
                }
                */
            }
        }
    }

    /** Checks if there is a collision between two entities.
     *
     * @param A sprite of first entity
     * @param B sprite of second entity
     * @return true if there is a collision, false otherwise.
     */
    public static boolean isCollision( Sprite A, Sprite B )
    {
        if( A.getGlobalBounds().intersection( B.getGlobalBounds() ) == null ) { return false; }
        return true;
    }

    /** Creates test player. Uses the placeholder sprite. */
    public void createPlayerBeta()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( App.resources.textures.get( 1 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite, new Vector2f( 5, 5 ) ) );
        object.sprite.setPosition( 300, 300 );
        playerIndex = gameObjects.size() - 1;
    }

    public void createPlayerKnight()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.sprite.setPosition( 300, 300 );
        object.addBehaviour( new MotionBehaviour( object.sprite, new Vector2f( 5, 5 ) ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightIdle, App.resources.pointsOfOrigin.get( "knightIdle") ), StateBehaviour.State.IDLE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightRun, App.resources.pointsOfOrigin.get( "knightRun") ), StateBehaviour.State.MOVE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightAttack, App.resources.pointsOfOrigin.get( "knightAttack") ), StateBehaviour.State.ATTACK.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightDead, App.resources.pointsOfOrigin.get( "knightDead") ), StateBehaviour.State.DEAD.ordinal() );
        object.addBehaviour( new PlayerStateBehaviour( App.inputHandler, object ) );
        playerIndex = gameObjects.size() - 1;
        object.type = GameObject.Type.PLAYER;
    }

    /** Creates an enemy. Spawn position is across a circle around the screen. */
    public void createEnemy()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addBehaviour( new MotionBehaviour( object.sprite, new Vector2f( 5, 5 ) ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightIdle, App.resources.pointsOfOrigin.get( "knightIdle") ), StateBehaviour.State.IDLE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightRun, App.resources.pointsOfOrigin.get( "knightRun") ), StateBehaviour.State.MOVE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightAttack, App.resources.pointsOfOrigin.get( "knightAttack") ), StateBehaviour.State.ATTACK.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightDead, App.resources.pointsOfOrigin.get( "knightDead") ), StateBehaviour.State.DEAD.ordinal() );

        object.addBehaviour( new NPCStateBehaviour( object, gameObjects.get( playerIndex ).sprite ) );
        //object.addBehaviour( new AIBehaviour( object.motion, object.sprite, gameObjects.get( playerIndex ).sprite ) );
        object.sprite.setPosition( 300, 100 );
        object.type = GameObject.Type.ENEMY;
    }

    public void addHealth()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.sprite.setPosition(App.SCREEN_HEIGHT/2, App.SCREEN_WIDTH/2);
        object.type = GameObject.Type.PLAYER;
    }

    /** Creates an enemy. Spawn position is across a circle around the screen. */
    public void createEnemyRandom()
    {
        int angle = new Random().nextInt( 360 );
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( App.resources.textures.get( 1 ) ) );
        object.addBehaviour( new MotionBehaviour( object.sprite, new Vector2f( 5 ,5 ) ) );
        object.addBehaviour( new AIBehaviour( object.motion, object.sprite, gameObjects.get( playerIndex ).sprite ) );
        object.sprite.setPosition( new Vector2f( ( ( float ) Math.sin( Math.toRadians( angle ) ) )*500.0f + App.SCREEN_WIDTH/2, ( ( float ) Math.cos( Math.toRadians( angle ) ) )*500.0f + App.SCREEN_HEIGHT/2 ) );
        object.type = GameObject.Type.ENEMY;
    }

    /** Creates a projectile and moves it in a certain direction.
     *
     * @param tex the texture that is going to be used for the projectile.
     * @param pos the starting position of the projectile
     * @param direction the moving direction of the projectile.
     */
    public void createProjectile( Texture tex, Vector2f pos, Vector2f direction )
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( tex );
        object.sprite.setPosition( pos );
        object.addBehaviour( new MotionBehaviour( object.sprite, new Vector2f( 5, 5 ) ) );
        object.motion.velocity = direction;
        object.type = GameObject.Type.PLAYER_BULLET;
    }

    /** Destroy all objects that are out of the screen. */
    public void destroyOutOfBoundsObjects()
    {

        for( int i = gameObjects.size() - 1; i >= 0; i -- ) // Counts backwards for performance reasons.
        {
            if( gameObjects.get( i ).hasMotion() == true  )//&& gameObjects.get( i ).motion.isOutOfScreenBoundaries() == true )
            {
                gameObjects.remove( gameObjects.get( i ) );
            }
        }
    }

    /** Deallocate all objects that are already dead. */
    public void destroyDeadObjects()
    {
        for( int i = gameObjects.size() - 1; i >= 0; i -- )
        { if( gameObjects.get( i ).status == GameObject.Status.INACTIVE ) { gameObjects.remove( gameObjects.get( i ) ); } }
    }

    /** Wrapper function, handles all deallocations. */
    public void checkForDeallocations()
    {
        //destroyOutOfBoundsObjects();
        destroyDeadObjects();
    }

    public void resolveKills()
    {
        for( int i = gameObjects.size() - 1; i >= 0; i -- )
        {
            for( int j = gameObjects.size() - 1; j >= 0; j-- )
            {
                if( i != j )
                {
                    GameObject objectA = gameObjects.get( i );
                    GameObject objectB = gameObjects.get( j );

                    if( objectA.state.isAttacking( objectB ) )
                    {
                        objectB.health.set( objectB.health.get() - 10 );
                        //objectB.state.currentStat
                    }
                }
            }
        }
    }

    public void buildLevel()
    {
        List<String> data = readMapFromFile( "levelEditor.txt" );
        Texture tex = App.resources.textures.get( 5 );

        for( int i = 0; i < data.size(); ++ i )
        {
            char[] line = data.get( i ).toCharArray();

            for( int j = 0; j < line.length; ++ j )
            {
                if( line[ j ] == '0' )
                {
                    createDecoration( App.resources.textures.get( 5 ), new Vector2f( tex.getSize().x * ( 0.5f + j ), tex.getSize().y * ( 0.5f + i ) ) );
                }
            }
        }
    }
}
