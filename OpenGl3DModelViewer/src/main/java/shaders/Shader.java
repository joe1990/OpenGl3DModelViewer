package shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by michael on 16.11.2015.
 */
public class Shader {

    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    public Shader(String vertexFile, String fragmentFile){
        vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        //bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        //getAllUniformLocations();
    }

    public void start(){
        GL20.glUseProgram(programID);
    }

    public void stop(){
        GL20.glUseProgram(0);
    }

    public void cleanUp(){
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    public static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try {
            File projectRootPath = new File(".");
            String filePath = projectRootPath + "\\" + file;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
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
