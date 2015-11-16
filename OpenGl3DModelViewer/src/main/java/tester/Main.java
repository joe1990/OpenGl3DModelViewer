package tester;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import userInterface.DisplayManager;

/**
 * Created by holzer on 16.11.2015.
 */
public class Main {

    public static void main(String[] args) {


        DisplayManager.create();


        while(DisplayManager.isNotCloseRequested()){

            //Render

            DisplayManager.update();
        }

        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("Display driver version: " + Display.getVersion());
        System.out.println("LWJGL version: " + Sys.getVersion());

        DisplayManager.close();
    }
}
