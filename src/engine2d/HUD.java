
package engine2d;


import org.jsfml.system.Vector2f;

public class HUD
{

    private Vector2f pos = new Vector2f( 100, 50 );
    private GameObject health;
    private GameObject player;
    private float smoothness;
    private float previousScale = 1.0f;
    private float currentScale = 1.0f;

    public HUD( World world )
    {
        world.createDecoration( App.resources.textures.get( "health_box" ), pos );
        world.gameObjects.get( world.gameObjects.size() - 1 ).type = GameObject.Type.HUD;
        world.createDecoration( App.resources.textures.get( "health_bar_empty" ), pos );
        world.gameObjects.get( world.gameObjects.size() - 1 ).type = GameObject.Type.HUD;
        world.createDecoration( App.resources.textures.get( "health_bar_full" ), pos );
        health = world.gameObjects.get( world.gameObjects.size() - 1 );
        health.type = GameObject.Type.HUD;

        player = world.gameObjects.get( world.playerIndex );
    }

    public void update()
    {
        currentScale = (float)player.health.get()/(float)player.maxHealth.get();
        if( previousScale != currentScale )
        {
            health.sprite.setScale( currentScale <= previousScale ? previousScale -= 0.01f : (previousScale = currentScale), 1.0f );
        }
    }
}
