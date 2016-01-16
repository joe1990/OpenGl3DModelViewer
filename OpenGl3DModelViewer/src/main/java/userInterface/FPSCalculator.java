package userInterface;

import org.lwjgl.Sys;

/**
 * Berechnet die aktuelle Frames per Seconds (FPS).
 * FPS konnte gesteigert werden wegen der Anwendung von VAO und VBO.
 */
public class FPSCalculator {

    //Frames per Seconds
    private int fps;
    private long lastFPS;

    /**
     * Berechnet FPS.
     */
    public FPSCalculator(){
        lastFPS = getTime(); //lastFPS auf die aktuelle Zeit setzen.
    }


    /**
     * Aktualisiert FBS
     */
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Window.setTitle("FPS: " + fps);
            fps = 0; //FPS resetten
            lastFPS += 1000; //eine Sekunde dazufügen
        }
        fps++;
    }

    /**
     * Gibt die Zeit in Milisekunden zurück.
     * @return Zeit in Milisekunden
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

}

