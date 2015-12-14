package shaders;

import geometrie.Camera;
import geometrie.Light;
import geometrie.Maths;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by michael on 16.11.2015.
 */
public class ShaderCollection extends Shader{

    private static final String VERTEX_FILE = "src/main/java/shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShader.frag";


    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_transformationMatrix;
    private int location_lightPosition;
    private int location_lightColor;

    public ShaderCollection() {
        super(VERTEX_FILE, FRAGMENT_FILE);

        location_projectionMatrix = super.getUniformLocation("projection");
        location_viewMatrix = super.getUniformLocation("view");
        location_transformationMatrix = super.getUniformLocation("transformation");

        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColor = super.getUniformLocation("lightColor");

    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadLight(Light light){
        super.loadVector(location_lightPosition, light.getPosition());
        super.loadVector(location_lightColor, light.getColour());
    }
}
