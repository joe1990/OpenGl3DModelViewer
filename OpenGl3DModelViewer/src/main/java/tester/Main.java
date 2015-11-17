package tester;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import renderEngine.GPUInterface;
import renderEngine.OBJReader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.ShaderCollection;
import userInterface.DisplayManager;

/**
 * Created by holzer on 16.11.2015.
 */
public class Main {

    public static void main(String[] args) {

        DisplayManager.create();

        GPUInterface loader = new GPUInterface();
        Renderer renderer = new Renderer();
        ShaderCollection shader = new ShaderCollection();

/*
        Cube cube = new Cube();
        RawModel model = loader.loadVAO(cube.getVertices(), cube.getIndices());
*/

        RawModel dragon = OBJReader.loadObjModel("dragon", loader);

        while(DisplayManager.isNotCloseRequested()){
            renderer.prepare();


            renderer.render(dragon);
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
