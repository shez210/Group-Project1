package engine2d;


public interface GameState
{
    void update();
    void draw();

    static void changeState()
    {

    }
}
