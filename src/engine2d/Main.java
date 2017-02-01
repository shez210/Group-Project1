package engine2d;

public class Main
{
    public static void main( String args[] )
    {
        App.init();

        while( App.window.isOpen() )
        {
            App.inputHandler.handleWindowEvents();
            App.currentState.update();
            App.currentState.draw();
            //System.out.printf( "framerate is %d\n", 1000/timer.restart().asMilliseconds() );
            //or LockSupport.parkNanos and System.getnanos.
        }
    }
}
