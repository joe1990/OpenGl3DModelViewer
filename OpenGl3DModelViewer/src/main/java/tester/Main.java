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

        //init Light
        Light light = new Light(new Vector3f(0,100,100), new Vector3f(1,1,1));

        //init Camera
        Camera camera = new Camera();

        //RawModel coordSystemModel = loader.loadVAO(CoordSystem.getVertices(), CoordSystem.getIndices());

        //Grid
        RawModel lineModel = loader.loadVAO(Line.getVertices(), Line.getIndices());
        ArrayList<Entity> lineGrid = new ArrayList<Entity>();
        for(int i=0; i<11; i++) {
            //horizontal grid lines
            Entity h_xz = new Entity(lineModel, new Vector3f(0, 0, i), 0, 0, 0, 10);
            lineGrid.add(h_xz);
            Entity h_xnz = new Entity(lineModel, new Vector3f(0, 0, -i), 0, 0, 0, 10);
            lineGrid.add(h_xnz);
            Entity h_nxz = new Entity(lineModel, new Vector3f(0, 0, i), 0, 180, 0, 10);
            lineGrid.add(h_nxz);
            Entity h_nxnz = new Entity(lineModel, new Vector3f(0, 0, -i), 0, 180, 0, 10);
            lineGrid.add(h_nxnz);

            //vertical grid lines
            Entity v_xz = new Entity(lineModel, new Vector3f(i, 0, 0), 0, 90, 0, 10);
            lineGrid.add(v_xz);
            Entity v_xnz = new Entity(lineModel, new Vector3f(-i, 0, 0), 0, 90, 0, 10);
            lineGrid.add(v_xnz);
            Entity v_nxz = new Entity(lineModel, new Vector3f(i, 0, 0), 0, 270, 0, 10);
            lineGrid.add(v_nxz);
            Entity v_nxnz = new Entity(lineModel, new Vector3f(-i, 0, 0), 0, 270, 0, 10);
            lineGrid.add(v_nxnz);
        }

        //OpenGL main loop
        while(DisplayManager.isNotCloseRequested()){

            //Render logic
            camera.move();
            renderer.prepare();

            shader.start(); //Start shader

            shader.loadLight(light);
            shader.loadViewMatrix(camera);

          //  renderer.renderLine(coordSystemModel);
          //  renderer.render(entity, shader);

            //Render grid
            for(Entity line:lineGrid) {
                renderer.renderLine(line, shader);
            }

            //Render models
            File wavefrontFile = Window.getWavefrontFile();
            if(wavefrontFile != null){
                RawModel rawModel = OBJReader.loadObjModel(wavefrontFile, loader);
                entity = new Entity(rawModel, new Vector3f(0,0,0) ,0,0,0,1);
            }

            if(entity != null) {
                renderer.render(entity, shader);
            }

            shader.stop(); //stop shader
            DisplayManager.update();
        }

        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("Display driver version: " + Display.getVersion());
        System.out.println("LWJGL version: " + Sys.getVersion());

        //Delete Shader, VAO and VBO
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.close();
    }
}
