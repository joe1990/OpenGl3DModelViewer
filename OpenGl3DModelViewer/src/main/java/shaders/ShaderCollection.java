package shaders;

import geometrie.Camera;
import geometrie.Light;
import geometrie.Maths;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by michael on 16.11.2015.
 */
public class ShaderCollection extends Shader{

    private static final String PATH_VERTEXSHADER = "src/main/java/shaders/vertexShader.vert";
    private static final String PATH_FRAGMENTSHADER= "src/main/java/shaders/fragmentShader.frag";

    private int lightPositionNr;
    private int lightColorNr;

    private int projectionNr;
    private int viewNr;
    private int transformationNr;


    public ShaderCollection() {
        super(PATH_VERTEXSHADER, PATH_FRAGMENTSHADER);

        lightPositionNr = GL20.glGetUniformLocation(progNr, "light");
        lightColorNr = GL20.glGetUniformLocation(progNr, "lightColor");
        projectionNr = GL20.glGetUniformLocation(progNr, "projection");
        transformationNr = GL20.glGetUniformLocation(progNr, "transformation");
        viewNr = GL20.glGetUniformLocation(progNr, "view");
    }

    /**
     * load a projection matrix
     * @param projection
     */
    public void loadProjectionMatrix(Matrix4f projection){
        loadMatrix(projectionNr, projection);
    }

    /**
     * loads a view matrix
     * @param camera
     */
    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        loadMatrix(viewNr, viewMatrix);
    }

    /**
     * loads a transformation matrix
     * @param matrix
     */
    public void loadTransformationMatrix(Matrix4f matrix){
        loadMatrix(transformationNr, matrix);
    }

    /**
     * loads a light
     * @param light
     */
    public void loadLight(Light light){
        GL20.glUniform3f(lightPositionNr, light.getPosition().x, light.getPosition().y, light.getPosition().z);
        GL20.glUniform3f(lightColorNr, light.getColour().x, light.getColour().y, light.getColour().z);
    }

    /**
     * load matrix
     * @param location
     * @param matrix
     */
    public void loadMatrix(int location, Matrix4f matrix){
        matrix.store(floatBuffer);
        floatBuffer.flip(); //ready to read
        GL20.glUniformMatrix4(location, false, floatBuffer);
    }
}
