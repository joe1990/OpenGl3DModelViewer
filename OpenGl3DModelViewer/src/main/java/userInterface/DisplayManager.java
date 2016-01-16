package userInterface;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 * Diese Klasse wird benötigt, um die OpenGL-Modelle, welche im Main-Loop erstellt wurden, in dem Fenster der
 * Java-Applikation anzuzeigen.
 */
public class DisplayManager {

    private static final int fps = 120;
    private static Window window;
    private static FPSCalculator fpsCalc;

    /**
     * Erstellt die Darstellung.
     */
    public static void create(){

        //OpenGL Version 4.1 verwenden. Diese wird von den Grafikkarten beider Entwickler (Studenten) unterstützt.
        ContextAttribs attribs = new ContextAttribs(4, 1);
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);

        window = new Window();
        fpsCalc = new FPSCalculator();

        try{
            Display.setParent(window.getCanvas());
            Display.create(new PixelFormat(), attribs);
        }catch(LWJGLException e){
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, window.getCanvasWidth(), window.getCanvasHeight());
    }

    /**
     * Prüft ob der User das Fenster geschlossen hat.
     *
     * @return True = User hat Fenster geschlosse, False = User hat Fenster nicht geschlossen.
     */
    public static boolean isNotCloseRequested(){
        return !Display.isCloseRequested() && !Window.closeRequested;
    }

    /**
     * Aktualisiert die Anzeige.
     */
    public static void update(){
        Display.sync(fps);
        Display.update();
        fpsCalc.updateFPS();
    }

    /**
     * Schliesst das Fenster.
     */
    public static void close(){
        Display.destroy();
        window.dispose();
        System.exit(0);
    }
}
