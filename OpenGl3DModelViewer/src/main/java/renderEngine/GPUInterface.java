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

    private ArrayList<Integer> vbo_list = new ArrayList<Integer>();
    private ArrayList<Integer> vao_list = new ArrayList<Integer>();

    /**
     * loads data into a VAO
     * @param positions
     * @param indices
     * @param normals
     * @param colour
     * @return
     */
    public Model loadVAO(float[] positions, int[] indices, float normals[], Color colour){

        int vaoID = GL30.glGenVertexArrays();
        vao_list.add(vaoID);
        GL30.glBindVertexArray(vaoID);

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
        return new Model(vaoID, indices.length);
    }

    /**
     * loads data into a VAO
     * @param positions
     * @param indices
     * @return
     */
    public Model loadVAO(float[] positions, int[] indices){

        int vaoID = GL30.glGenVertexArrays();
        vao_list.add(vaoID);
        GL30.glBindVertexArray(vaoID);

        bindIndicesBuffer(indices);
        storeDataInAttributList(0, 3, positions);
        GL30.glBindVertexArray(0);
        return new Model(vaoID, indices.length);
    }

    /**
     * clean up
     */
    public void cleanUp(){
        for(int vao:vao_list){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo:vbo_list){
            GL30.glDeleteFramebuffers(vbo);
        }
    }


    /**
     * stores data in attribut list
     * @param attribut
     * @param coords
     * @param data
     */
    private void storeDataInAttributList(int attribut, int coords, float[] data){
        int vboID = GL15.glGenBuffers();
        vbo_list.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = this.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribut, coords, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    /**
     * binds indices buffer
     * @param indices
     */
    private void  bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbo_list.add(vboID);
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
