package userInterface;

import org.lwjgl.Sys;

/**
 * Created by michael on 16.11.2015.
 */

public class FPSCalculator {

    int fps; //frames per seconds
    long lastFPS;

    public FPSCalculator(){
        lastFPS = getTime(); //set lastFPS to current Time
    }



    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Window.setTitle("FPS: " + fps);
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
    }


    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

}

