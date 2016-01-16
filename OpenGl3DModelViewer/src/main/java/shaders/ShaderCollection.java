package shaders;

import geometrie.Camera;
import geometrie.Light;
import geometrie.Maths;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Erstellt eine Collection aus einem Vertex- und eine Fragment-Shader.
 */
public class ShaderCollection extends Shader{

    private static final String PATH_VERTEXSHADER = "src/main/java/shaders/vertexShader.vert";
    private static final String PATH_FRAGMENTSHADER= "src/main/java/shaders/fragmentShader.frag";

    private int lightPositionNr;
    private int lightColorNr;

    private int projectionNr;
    private int viewNr;
    private int transformationNr;

    /**
     * Konstruktor.
     */
    public ShaderCollection() {
        super(PATH_VERTEXSHADER, PATH_FRAGMENTSHADER);

        lightPositionNr = GL20.glGetUniformLocation(this.getProgNr(), "light");
        lightColorNr = GL20.glGetUniformLocation(this.getProgNr(), "lightColor");
        projectionNr = GL20.glGetUniformLocation(this.getProgNr(), "projection");
        transformationNr = GL20.glGetUniformLocation(this.getProgNr(), "transformation");
        viewNr = GL20.glGetUniformLocation(this.getProgNr(), "view");
    }

    /**
     * Lädt einen Projektions-Matrix.
     * @param projection Projektions-Matrix, welche geladen werden soll.
     */
    public void loadProjectionMatrix(Matrix4f projection){
        loadMatrix(projectionNr, projection);
    }

    /**
     * Lädt eine View-Matrix.
     * @param camera View-Matrix für die Kamera, welche geladen werden soll.
     */
    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        loadMatrix(viewNr, viewMatrix);
    }

    /**
     * Lädt eine Transformations-Matrix
     * @param matrix Transformations-Matrix, welche geladen werden soll.
     */
    public void loadTransformationMatrix(Matrix4f matrix){
        loadMatrix(transformationNr, matrix);
    }

    /**
     * Lädt ein Punktlicht
     * @param light Punktlicht, welches geladen werden soll.
     */
    public void loadLight(Light light){
        GL20.glUniform3f(lightPositionNr, light.getPosition().x, light.getPosition().y, light.getPosition().z);
        GL20.glUniform3f(lightColorNr, light.getColour().x, light.getColour().y, light.getColour().z);
    }

    /**
     * Lädt eine Matrix.
     *
     * @param location
     * @param matrix
     */
    public void loadMatrix(int location, Matrix4f matrix){
        matrix.store(floatBuffer);
        floatBuffer.flip(); //Ready zum Lesen
        GL20.glUniformMatrix4(location, false, floatBuffer);
    }
}
