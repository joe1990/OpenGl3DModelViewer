package shaders;

/**
 * Created by michael on 16.11.2015.
 */
public class ShaderCollection {

    private static final String VERTEX_file = "src/main/java/shaders/vertexShader.vert";
    private static final String FRAGMENT_file = "src/main/java/shaders/fragmentShader.frag";

    public ShaderCollection() {
        Shader sh = new Shader(VERTEX_file, FRAGMENT_file);

    }
}
