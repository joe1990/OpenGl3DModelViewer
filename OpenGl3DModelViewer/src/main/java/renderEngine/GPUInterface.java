package renderEngine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.awt.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

/**
 * Created by michael on 16.11.2015.
 *
 * GPUInterface class - load data into the graphics card
 */
public class GPUInterface {

    private ArrayList<Integer> vaos = new ArrayList<Integer>();
    private ArrayList<Integer> vbos = new ArrayList<Integer>();

    /**
     * loads data into a VAO
     * @param positions
     * @param indices
     * @param normals
     * @param colour
     * @return
     */
    public RawModel loadVAO(float[] positions, int[] indices, float normals[], Color colour){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributList(0, 3, positions);
        storeDataInAttributList(1, 3, normals);

        float[] colours = new float[positions.length];

        for(int i=0; i<positions.length; i+=3){
            colours[i] = colour.getRed()/255;
            colours[i+1] = colour.getGreen()/255;
            colours[i+2] = colour.getBlue()/255;
        }

        storeDataInAttributList(2, 3, colours);
        GL30.glBindVertexArray(0);
        return new RawModel(vaoID, indices.length);
    }

    /**
     * loads data into a VAO
     * @param positions
     * @param indices
     * @return
     */
    public RawModel loadVAO(float[] positions, int[] indices){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributList(0, 3, positions);
        GL30.glBindVertexArray(0);
        return new RawModel(vaoID, indices.length);
    }

    /**
     * clean up
     */
    public void cleanUp(){
        for(int vao:vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo:vbos){
            GL30.glDeleteFramebuffers(vbo);
        }
    }

    /**
     * @return vao id
     */
    private int createVAO(){
        int voaID = GL30.glGenVertexArrays();
        vaos.add(voaID);
        GL30.glBindVertexArray(voaID);
        return voaID;
    }

    /**
     * stores data in attribut list
     * @param attributNumber
     * @param coordSize
     * @param data
     */
    private void storeDataInAttributList(int attributNumber, int coordSize, float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = this.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributNumber, coordSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * binds indices buffer
     * @param indices
     */
    private void  bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = this.storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    /**
     * store data in Int buffer
     * @param data
     * @return
     */
    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    /**
     * store data in Float buffer
     * @param data
     * @return
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
