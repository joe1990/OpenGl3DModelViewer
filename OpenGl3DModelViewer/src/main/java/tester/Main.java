package tester;

import geometrie.Camera;
import geometrie.Light;
import geometrie.Line;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import shaders.ShaderCollection;
import userInterface.DisplayManager;
import userInterface.Window;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by holzer on 16.11.2015.
 */
public class Main {


    public static void main(String[] args) {

        DisplayManager.create();
        Entity entity = null;

        GPUInterface loader = new GPUInterface();
        ShaderCollection shader = new ShaderCollection();
        Renderer renderer = new Renderer(shader);

        Light light = new Light(new Vector3f(0,100,100), new Vector3f(1,1,1));
        Camera camera = new Camera();

       // RawModel coordSystemModel = loader.loadVAO(CoordSystem.getVertices(), CoordSystem.getIndices());
       // RawModel dragonModel = OBJReader.loadObjModel("bunny", loader);
       // Entity entity = new Entity(dragonModel, new Vector3f(0,0,0) ,0,0,0,1);


        //Gitternetz
        RawModel lineModel = loader.loadVAO(Line.getVertices(), Line.getIndices());
        ArrayList<Entity> lineGrid = new ArrayList<Entity>();

        Entity lineEntity = new Entity(lineModel, new Vector3f(0,0,0) ,0,0,0,1);
        lineGrid.add(lineEntity);

        while(DisplayManager.isNotCloseRequested()){
            camera.move();
            renderer.prepare();
            shader.start();

            shader.loadLight(light);
            shader.loadViewMatrix(camera);

          //  renderer.renderLine(coordSystemModel);
          //  renderer.render(entity, shader);

            for(Entity line:lineGrid) {
                renderer.renderLine(line, shader);
            }

            File wavefrontFile = Window.getWavefrontFile();
            if(wavefrontFile != null){
                RawModel dragonModel = OBJReader.loadObjModel(wavefrontFile, loader);
                entity = new Entity(dragonModel, new Vector3f(0,0,0) ,0,0,0,1);
            }

            if(entity != null) {
                renderer.render(entity, shader);
            }

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
