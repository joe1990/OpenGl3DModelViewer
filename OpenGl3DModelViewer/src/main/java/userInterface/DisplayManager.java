package userInterface;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 * Created by michael on 16.11.2015.
 */
public class DisplayManager {

    private static final int fps = 120;
    public static Window window = new Window();
    public static FPSCalculator fpsCalc = new FPSCalculator();

    public static void create(){

        ContextAttribs attribs = new ContextAttribs(4, 1); //Use OpenGL Version 4.1
        attribs.withForwardCompatible(true);
        attribs.withProfileCore(true);

        try{
            Display.setParent(window.getCanvas());
            Display.create(new PixelFormat(), attribs);
        }catch(LWJGLException e){
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, window.getCanvasWidth(), window.getCanvasHeiht());
    }

    public static boolean isNotCloseRequested(){
        return !Display.isCloseRequested() && !Window.closeRequested;
    }

    public static void update(){
        Display.sync(fps);
        Display.update();
        fpsCalc.updateFPS();
    }


    public static void close(){
        Display.destroy();
        window.dispose();
        System.exit(0);
    }

}
