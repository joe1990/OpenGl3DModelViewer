package shaders;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;

/**
 * Created by michael on 16.11.2015
 *
 * Shader class
 */
public class Shader {

    public int progNr;
    public static FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16); //4x4

    private int vertexShaderNr;
    private int fragmentShaderNr;

    /**
     *
     * @param vertexPath
     * @param fragmentPath
     */
    public Shader(String vertexPath, String fragmentPath){
        progNr = GL20.glCreateProgram();

        fragmentShaderNr = loadShader(fragmentPath, GL20.GL_FRAGMENT_SHADER);
        vertexShaderNr = loadShader(vertexPath, GL20.GL_VERTEX_SHADER);

        GL20.glAttachShader(progNr, vertexShaderNr);
        GL20.glAttachShader(progNr, fragmentShaderNr);

        GL20.glBindAttribLocation(progNr, 0, "position"); //Anstelle von glGetAttribLocation
        GL20.glBindAttribLocation(progNr, 1, "normal");
        GL20.glBindAttribLocation(progNr, 2, "colour");

        GL20.glLinkProgram(progNr);
        GL20.glValidateProgram(progNr);
    }

    /**
     * starts the shader
     */
    public void start(){
        GL20.glUseProgram(progNr);
    }

    /**
     * stops the shader
     */
    public void stop(){
        GL20.glUseProgram(0);
    }

    /**
     * clean up
     */
    public void cleanUp(){
        stop();
        GL20.glDetachShader(progNr, vertexShaderNr);
        GL20.glDetachShader(progNr, fragmentShaderNr);
        GL20.glDeleteShader(vertexShaderNr);
        GL20.glDeleteShader(fragmentShaderNr);
        GL20.glDeleteProgram(progNr);
    }

    /**
     * compile and load the shader
     * @param file
     * @param type
     * @return shader id
     */
    public static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null){
                shaderSource.append(line).append("\n");{
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Could not read file! - "+file);
            e.printStackTrace();
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS)== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 5000));
            System.err.println("Could not compile shader with id: "+shaderID);
        }
        return shaderID;
    }
}
