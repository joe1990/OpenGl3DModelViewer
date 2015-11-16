package tester;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import renderEngine.PGUInterface;
import renderEngine.RawModel;
import renderEngine.Renderer;
import geometrie.Cube;
import userInterface.DisplayManager;

/**
 * Created by holzer on 16.11.2015.
 */
public class Main {

    public static void main(String[] args) {

        DisplayManager.create();

        PGUInterface loader = new PGUInterface();
        Renderer renderer = new Renderer();

        Cube cube = new Cube();
        RawModel model = loader.loadVAO(cube.getVertices(), cube.getIndices());

        while(DisplayManager.isNotCloseRequested()){
            renderer.prepare();


            renderer.render(model);
            DisplayManager.update();
        }

        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("Display driver version: " + Display.getVersion());
        System.out.println("LWJGL version: " + Sys.getVersion());
        System.out.println("Slick version: ");

        loader.cleanUp();
        DisplayManager.close();
    }
}
