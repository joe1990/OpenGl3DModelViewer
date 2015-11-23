package tester;

import geometrie.Camera;
import geometrie.CoordSystem;
import geometrie.Light;
import geometrie.Line;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
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
        ShaderCollection shader = new ShaderCollection();
        Renderer renderer = new Renderer(shader);
        Camera camera = new Camera();
        Light light = new Light(new Vector3f(0,0,0), new Vector3f(1,1,1));

/*
        Cube cube = new Cube();
        RawModel model = loader.loadVAO(cube.getVertices(), cube.getIndices());
*/
        CoordSystem coordSystem = new CoordSystem();
        RawModel coordSystemModel = loader.loadVAO(coordSystem.getVertices(), coordSystem.getIndices());


        Line line = new Line(0, -10, 0, 10);
        RawModel lineModel = loader.loadVAO(line.getVertices(), line.getIndices());

        RawModel dragonModel = OBJReader.loadObjModel("dragon", loader);


        while(DisplayManager.isNotCloseRequested()){
            camera.move();
            renderer.prepare();
            shader.start();

            shader.loadLight(light);
            shader.loadViewMatrix(camera);

            renderer.render(dragonModel);
            renderer.renderLine(coordSystemModel);
            renderer.renderLine(lineModel);

            shader.stop();
            DisplayManager.update();
        }

        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("Display driver version: " + Display.getVersion());
        System.out.println("LWJGL version: " + Sys.getVersion());
        System.out.println("Slick version: ");

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.close();
    }
}
