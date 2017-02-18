package engine2d;

import engine2d.behaviour.*;
import org.jsfml.graphics.*;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;

import java.util.Collections;
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
        //createPlayer();
        //createPlayer();
        //createPlayerBeta();
        buildLevel();
        //createPlayerKnight();
        //createEnemy();
        //addHealth();
        //createEnemyRandom();
        //createDecoration( App.resources.knightIdle.get( 1 ), new Vector2f( 50, 50 ) );
    }


    /** Handles creation and destruction of entities and also resolves collision. */
    public void update()
    {
        for( GameObject object : gameObjects ) { object.update(); }
        moveEntitiesAlong( ColliderBehaviour.Axis.X );
        resolveCollisionsAlong( ColliderBehaviour.Axis.X );
        moveEntitiesAlong( ColliderBehaviour.Axis.Y );
        resolveCollisionsAlong( ColliderBehaviour.Axis.Y );
        scrollAllEntities();

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

        for( GameObject object : gameObjects )
        {
            if( object.shadow != null ) { App.window.draw( object.shadow ); }
            App.window.draw( object.sprite );
        }
        App.window.draw( new Text( "Use WASD to move and Space to attack.", App.resources.font, 30 ) );
        //App.window.draw( new Text( "\nhealth = " + gameObjects.get( playerIndex ).health, App.resources.font, 30 ) );
        //if( gameObjects.size() - 1 >= playerIndex + 1 )
        //{
          //  App.window.draw( new Text( "\n                             enemy health = " + gameObjects.get( playerIndex + 1 ).health, App.resources.font, 30 ) );
        //}

        App.window.draw( App.resources.cursorSprite );
        App.window.display();
    }

    public void resolveCollisionsAlong( ColliderBehaviour.Axis axis )
    {
        for( int i = gameObjects.size() - 1; i >= 0; i -- )
        {
            for( int j = gameObjects.size() - 1; j >= 0; j -- )
            {
                GameObject objectA = gameObjects.get( i );
                GameObject objectB = gameObjects.get( j );
                FloatRect box = objectA.sprite.getGlobalBounds();
                if( i != j && (objectA.type == GameObject.Type.PLAYER ) && objectB.collidable
                        && isCollision( new FloatRect( box.left + 50, box.top + 100, 10, 10 ), objectB.sprite.getGlobalBounds() ) )
                {
                    objectA.collider.inCollisionWith( objectB, axis );
                }
            }
        }
    }

    public void moveEntitiesAlong( ColliderBehaviour.Axis axis )
    {
        for( GameObject object : gameObjects )
        {
            if( object.collider != null )
            {
                Vector2f move;
                if( axis == ColliderBehaviour.Axis.X ) { move = new Vector2f( object.collider.velocity.x, 0 ); }
                else move = new Vector2f( 0, object.collider.velocity.y );
                object.sprite.setPosition( Vector2f.add( object.sprite.getPosition(), move ) );
            }
        }
    }

    public void scrollAllEntities()
    {
        for( GameObject object : gameObjects )
        {
            if( gameObjects.get( playerIndex ).collider.hasCollidedAlong[ ColliderBehaviour.Axis.X.ordinal() ] == false )
            {
                object.sprite.setPosition( Vector2f.sub( object.sprite.getPosition(), new Vector2f( gameObjects.get( playerIndex ).collider.velocity.x, 0 ) ) );
            }
            if( gameObjects.get( playerIndex ).collider.hasCollidedAlong[ ColliderBehaviour.Axis.Y.ordinal() ] == false )
            {
                object.sprite.setPosition( Vector2f.sub( object.sprite.getPosition(), new Vector2f( 0, gameObjects.get( playerIndex ).collider.velocity.y ) ) );
            }
        }
    }

    /** Checks if there is a collision between two entities.
     *
     * @param A sprite of first entity
     * @param B sprite of second entity
     * @return true if there is a collision, false otherwise.
     */
    public static boolean isCollision( FloatRect A, FloatRect B )
    {
        if( A.intersection( B ) == null ) { return false; }
        return true;
    }

    /** Creates test player. Uses the placeholder sprite. */
    public void createPlayerBeta()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( App.resources.textures.get( 1 ) ) );
        object.addBehaviour( new ColliderBehaviour( object.sprite, 5 ) );
        object.sprite.setPosition( 300, 300 );
        playerIndex = gameObjects.size() - 1;
    }

    public void createPlayerKnight()
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.health.set( 500 );
        object.sprite.setPosition( App.SCREEN_WIDTH/2, App.SCREEN_HEIGHT/2 );
        object.shadow = new Sprite( App.resources.textures.get( "shadow" ) );
        object.shadow.setOrigin( object.shadow.getGlobalBounds().width/2, object.shadow.getGlobalBounds().height/2 );
        object.shadow.setScale( 0.1f, 0.1f );
        object.shadow.setColor( new Color( object.shadow.getColor().r, object.shadow.getColor().b, object.shadow.getColor().g, 128 ) );
        object.addBehaviour( new ColliderBehaviour( object.sprite, 1.5f ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightIdle, App.resources.pointsOfOrigin.get( "knightIdle") ), StateBehaviour.State.IDLE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightRun, App.resources.pointsOfOrigin.get( "knightRun") ), StateBehaviour.State.MOVE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightAttack, App.resources.pointsOfOrigin.get( "knightAttack") ), StateBehaviour.State.ATTACK.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightDead, App.resources.pointsOfOrigin.get( "knightDead") ), StateBehaviour.State.DEAD.ordinal() );
        object.addBehaviour( new PlayerStateBehaviour( App.inputHandler, object ) );
        playerIndex = gameObjects.size() - 1;
        object.type = GameObject.Type.PLAYER;
    }

    /** Creates an enemy. Spawn position is across a circle around the screen. */
    public void createEnemy( Vector2f pos )
    {
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.shadow = new Sprite( App.resources.textures.get( "shadow" ) );
        object.shadow.setOrigin( object.shadow.getGlobalBounds().width/2, object.shadow.getGlobalBounds().height/2 );
        object.shadow.setScale( 0.1f, 0.1f );
        object.shadow.setColor( new Color( object.shadow.getColor().r, object.shadow.getColor().b, object.shadow.getColor().g, 128 ) );
        object.addBehaviour( new ColliderBehaviour( object.sprite, 5 ) );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightIdle, App.resources.pointsOfOrigin.get( "knightIdle") ), StateBehaviour.State.IDLE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightRun, App.resources.pointsOfOrigin.get( "knightRun") ), StateBehaviour.State.MOVE.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightAttack, App.resources.pointsOfOrigin.get( "knightAttack") ), StateBehaviour.State.ATTACK.ordinal() );
        object.addBehaviour( new AnimationBehaviour( object.sprite, App.resources.knightDead, App.resources.pointsOfOrigin.get( "knightDead") ), StateBehaviour.State.DEAD.ordinal() );
        object.addBehaviour( new NPCStateBehaviour( object, gameObjects.get( playerIndex ).sprite ) );
        object.sprite.setPosition( pos );
        object.type = GameObject.Type.ENEMY;
    }

    /** Creates an enemy. Spawn position is across a circle around the screen. */
    public void createEnemyRandom()
    {
        int angle = new Random().nextInt( 360 );
        GameObject object = new GameObject();
        gameObjects.add( object );
        object.addTexture( new Texture( App.resources.textures.get( 1 ) ) );
        object.addBehaviour( new ColliderBehaviour( object.sprite, 5 ) );
        object.addBehaviour( new AIBehaviour( object.collider, object.sprite, gameObjects.get( playerIndex ).sprite ) );
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
        object.addBehaviour( new ColliderBehaviour( object.sprite, 5 ) );
        object.collider.velocity = direction;
        object.type = GameObject.Type.PLAYER_BULLET;
    }

    /** Destroy all objects that are out of the screen. */
    public void destroyOutOfBoundsObjects()
    {
        for( int i = gameObjects.size() - 1; i >= 0; i -- ) // Counts backwards for performance reasons.
        {
            if( gameObjects.get( i ).hasMotion() == true  ) { gameObjects.remove( gameObjects.get( i ) ); }
        }
    }

    /** Deallocate all objects that are already dead. */
    public void destroyDeadObjects()
    {
        for( int i = gameObjects.size() - 1; i >= 0; i -- )
        {
            if( gameObjects.get( i ).status == GameObject.Status.INACTIVE )
            {
                Collections.swap( gameObjects, i, gameObjects.size() - 1 );
                gameObjects.remove( gameObjects.size() - 1 );
            }
        }
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

                    if( objectA.state != null && objectA.state.isAttacking( objectB ) )
                    {
                        objectB.health.set( objectB.health.get() - 50 );
                        //objectB.state.currentStat
                    }
                }
            }
        }
    }

    public void buildLevel()
    {
        List<String> data = readMapFromFile( "levelEditor.txt" );

        int texSide = 114;
        int maxTileIndex = 3;

        for( int i = 0; i < data.size(); ++ i )
        {
            char[] line = data.get( i ).toCharArray();

            for( int j = 0; j < line.length; ++ j )
            {
                float x = texSide * ( j );
                float y = texSide * ( i );
                Vector2f pos = new Vector2f( x, y );

                if( line[ j ] == '0' || line[ j ] == '2' )
                {
                    createDecoration( App.resources.textures.get( "stoneFloor1" ), pos );
                }

                if( line[ j ] == '1' )
                {
                    createWallTexture( i, j, maxTileIndex, pos, data );
                }
            }
        }

        for( int i = 0; i < data.size(); ++ i )
        {
            char[] line = data.get( i ).toCharArray();
            for( int j = 0; j < line.length; ++ j )
            {
                float x = texSide * ( j );
                float y = texSide * ( i );
                Vector2f pos = new Vector2f( x, y );

                if( line[ j ] == '2' )
                {
                    if( playerIndex < 0 ) { createPlayerKnight(); }
                    createEnemy( pos );
                }
            }
        }
    }

    /** messed up level editor code */
    public void createWallTexture( int i, int j, int maxTileIndex, Vector2f pos, List<String> data )
    {
        char[] topRow = data.get( i - 1 ).toCharArray();
        char[] currentRow = data.get( i ).toCharArray();
        char[] bottomRow = data.get( i + 1 ).toCharArray();

        if( currentRow[ j - 1 ] == '1' && currentRow[ j + 1 ] == '1' && bottomRow[ j ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallNorth1" ), pos, true );
        }
        if( currentRow[ j - 1 ] == '1' && currentRow[ j + 1 ] == '1' && topRow[ j ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallSouth1" ), pos, true );
        }
        if( topRow[ j ] == '1' && bottomRow[ j ] == '1' && currentRow[ j + 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallWest1" ), pos, true );
        }
        if( topRow[ j ] == '1' && bottomRow[ j ] == '1' && currentRow[ j - 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallEast1" ), pos, true );
        }

        if( currentRow[ j + 1 ] == '1' && bottomRow[ j ] == '1' && bottomRow[ j + 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallNorthWest1" ), pos, true );
        }
        if( currentRow[ j - 1 ] == '1' && bottomRow[ j ] == '1' && bottomRow[ j - 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallNorthEast1" ), pos, true );
        }
        if( currentRow[ j + 1 ] == '1' && topRow[ j ] == '1' && topRow[ j + 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallSouthWest1" ), pos, true );
        }
        if( currentRow[ j - 1 ] == '1' && topRow[ j ] == '1'&& topRow[ j - 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallSouthEast1" ), pos, true );
        }

        if( currentRow[ j + 1 ] == '1' && bottomRow[ j ] == '1' && topRow[ j - 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallNorthWestInverted1" ), pos, true );
        }
        if( currentRow[ j - 1 ] == '1' && bottomRow[ j ] == '1' && topRow[ j + 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallNorthEastInverted1" ), pos, true );
        }
        if( currentRow[ j + 1 ] == '1' && topRow[ j ] == '1' && bottomRow[ j - 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallSouthWestInverted1" ), pos, true );
        }
        if( currentRow[ j - 1 ] == '1' && topRow[ j ] == '1'&& bottomRow[ j + 1 ] == '0' )
        {
            createDecoration( App.resources.textures.get( "stoneWallSouthEastInverted1" ), pos, true );
        }


        //else if( )
    }
}
