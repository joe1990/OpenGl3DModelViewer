package tester;

import geometrie.Camera;
import geometrie.CoordSystem;
import geometrie.Light;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import shaders.ShaderCollection;
import userInterface.DisplayManager;

/**
 * Created by holzer on 16.11.2015.
 */
public class Main {



    public static void main(String[] args) {

        DisplayManager.create();

        GPUInterface loader = new GPUInterface();
        ShaderCollection shader = new ShaderCollection();
        Renderer renderer = new Renderer(shader);


        Light light = new Light(new Vector3f(0,100,100), new Vector3f(0.5f,1,1));

        RawModel coordSystemModel = loader.loadVAO(CoordSystem.getVertices(), CoordSystem.getIndices());


        RawModel dragonModel = OBJReader.loadObjModel("dragon", loader);
        Entity dragonEntity = new Entity(dragonModel, new Vector3f(0,0,0) ,0,0,0,1); //Translation: (0,-5,20) and Scale: 0.8

        Camera camera = new Camera();

        while(DisplayManager.isNotCloseRequested()){
            camera.move();
            renderer.prepare();
            shader.start();

            shader.loadLight(light);
            shader.loadViewMatrix(camera);

            renderer.renderLine(coordSystemModel);

            renderer.render(dragonEntity, shader);

            shader.stop();
            DisplayManager.update();
        }

        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("Display driver version: " + Display.getVersion());
        System.out.println("LWJGL version: " + Sys.getVersion());

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.close();
    }
}
