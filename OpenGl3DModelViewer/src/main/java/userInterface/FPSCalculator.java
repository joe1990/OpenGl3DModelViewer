package userInterface;

import org.lwjgl.Sys;

/**
 * Created by michael on 16.11.2015.
 *
 * calculates the current FPS
 */

public class FPSCalculator {

    //frames per seconds
    private int fps;
    private long lastFPS;

    /**
     * calculate FPS
     */
    public FPSCalculator(){
        lastFPS = getTime(); //set lastFPS to current Time
    }


    /**
     * update FPS
     */
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Window.setTitle("FPS: " + fps);
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
    }

    /**
     * @return time in millisec
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

}

