package engine2d;


import org.jsfml.system.Clock;

public interface GameState
{
    float TOGGLE_DELAY = 1.0f;
    Clock toggleDelayTimer = new Clock();

    void update();
    void draw();

}
