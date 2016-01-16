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

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Main-Klasse des OpenGL Wavefront Viewer.
 * Lädt das Gitternetz und das Punktlicht (Sonne) und wartet auf die Aktion des Users zum Laden des Wavefront OBJ-Files.
 */
public class Main {


    public static void main(String[] args) {

        DisplayManager.create();
        Entity entity = null;

        //Initialisiert den Shader
        GPUInterface loader = new GPUInterface();
        ShaderCollection shader = new ShaderCollection();
        Renderer renderer = new Renderer(shader);

        //Initialisiert das Punktlicht
        Light light = new Light(new Vector3f(20,20,0), new Vector3f(1,1,1));
        Model sunModel = OBJReader.loadObjModel(new File("ressources/sphere.obj"), loader, Color.yellow);
        Entity sunEntity = new Entity(sunModel, new Vector3f(20,20,0), 0, 0, 0, 1);

        //Initialisiert die Kamera
        Camera camera = new Camera();

        //Erstellt das Gitternetz
        Model lineModel = loader.loadVAO(Line.getVertices(), Line.getIndices());
        ArrayList<Entity> lineGrid = new ArrayList<Entity>();
        for(int i=0; i<21; i++) {

            //Horizontale Linien
            Entity h_xz = new Entity(lineModel, new Vector3f(0, 0, i), 0, 0, 0, 20);
            lineGrid.add(h_xz);
            Entity h_xnz = new Entity(lineModel, new Vector3f(0, 0, -i), 0, 0, 0, 20);
            lineGrid.add(h_xnz);
            Entity h_nxz = new Entity(lineModel, new Vector3f(0, 0, i), 0, 180, 0, 20);
            lineGrid.add(h_nxz);
            Entity h_nxnz = new Entity(lineModel, new Vector3f(0, 0, -i), 0, 180, 0, 20);
            lineGrid.add(h_nxnz);

            //Vertikale Linien
            Entity v_xz = new Entity(lineModel, new Vector3f(i, 0, 0), 0, 90, 0, 20);
            lineGrid.add(v_xz);
            Entity v_xnz = new Entity(lineModel, new Vector3f(-i, 0, 0), 0, 90, 0, 20);
            lineGrid.add(v_xnz);
            Entity v_nxz = new Entity(lineModel, new Vector3f(i, 0, 0), 0, 270, 0, 20);
            lineGrid.add(v_nxz);
            Entity v_nxnz = new Entity(lineModel, new Vector3f(-i, 0, 0), 0, 270, 0, 20);
            lineGrid.add(v_nxnz);
        }

        //Das hier ist der Main-Loop von Open-GL.
        while(DisplayManager.isNotCloseRequested()){

            //Render Logik
            camera.move();
            renderer.prepare();

            //Shader starten
            shader.start();

            shader.loadLight(light);
            shader.loadViewMatrix(camera);

            //Die Linien des Gitternetzes rendern
            for(Entity line : lineGrid) {
                renderer.renderLines(line, shader);
            }

            //Sonne (Punktlicht) rendern
            renderer.renderTriangles(sunEntity, shader);

            //3D-Model/Figur aus dem Wavefront OBJ-File rendern.
            File wavefrontFile = Window.getWavefrontFile();
            if(wavefrontFile != null){
                Model rawModel = OBJReader.loadObjModel(wavefrontFile, loader, Color.red);
                entity = new Entity(rawModel, new Vector3f(0,0,0) ,0,0,0,1);
            }

            if(entity != null) {
                renderer.renderTriangles(entity, shader);
            }

            //Shader stoppen und das GUI updated.
            shader.stop();
            DisplayManager.update();
        }

        //Dies sind Debug-Informationen.
        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        System.out.println("Display driver version: " + Display.getVersion());
        System.out.println("LWJGL version: " + Sys.getVersion());

        //Shader, VAO and VBO wieder löschen.
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.close();
    }
}
