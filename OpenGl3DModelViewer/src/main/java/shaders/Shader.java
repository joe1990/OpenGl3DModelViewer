package shaders;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;

/**
 * Klasse um Fragment und Vertex-Shader aus den Shader-Files (.frag und .vert-Endung) zu Laden und diese zu
 * verarbeiten/anzuwenden.
 */
public class Shader {

    private int progNr;
    protected FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16); //4x4

    private int vertexShaderNr;
    private int fragmentShaderNr;

    /**
     * Erstellt einen neuen Shader für den übergebenen Fragment- und Vertex-Shader.
     *
     * @param vertexPath Pfad zum Vertex-Shader.
     * @param fragmentPath Pfad zum Fragment-Shader.
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
     * Gibt die ProgNr. zurück.
     * @return ProgNr.
     */
    public int getProgNr() {
        return this.progNr;
    }

    /**
     * Startet den Shader.
     */
    public void start(){
        GL20.glUseProgram(progNr);
    }

    /**
     * Stoppt den Shader.
     */
    public void stop(){
        GL20.glUseProgram(0);
    }

    /**
     * clean up den Shader.
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
     * Kompiliert und Lädt den Shader aus dem übergebenen Shader-File.
     * Gibt Fehlermeldungen auf der Konsole aus wenn das übergebene File nicht richtig gelesen werden konnte.
     *
     * @param file Shader-File (.frag- oder .vert-Endung)
     * @param type Typ des Shaders. GL20.GL_FRAGMENT_SHADER für Fragment-Shader, GL20.GL_VERTEX_SHADER für Vertex-Shader.
     * @return ID des erstellten Shaders.
     */
    private int loadShader(String file, int type){
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
