package renderEngine;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

/**
 * Created by michael on 16.11.2015.
 */
public class GPUInterface {
    private ArrayList<Integer> vaos = new ArrayList<Integer>();
    private ArrayList<Integer> vbos = new ArrayList<Integer>();
    private ArrayList<Integer> textures = new ArrayList<Integer>();


    public RawModel loadVAO(float[] positions, int[] indices, float normals[]){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDatainAttributList(0, 3, positions);
        storeDatainAttributList(1, 3, normals);
        unbind();
        return new RawModel(vaoID, indices.length);
    }

    public RawModel loadVAO(float[] positions, int[] indices){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDatainAttributList(0,3,positions);
        unbind();
        return new RawModel(vaoID, indices.length);
    }

    public void cleanUp(){
        for(int vao:vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for(int vbo:vbos){
            GL30.glDeleteFramebuffers(vbo);
        }
        for(int texture: textures){
            GL11.glDeleteTextures(texture);
        }
    }

    private int createVAO(){
        int voaID = GL30.glGenVertexArrays();
        vaos.add(voaID);
        GL30.glBindVertexArray(voaID);
        return voaID;
    }

    private void storeDatainAttributList(int attributNumber, int coordSize, float[] data){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = this.storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributNumber, coordSize, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);


    }
    private void unbind(){
        GL30.glBindVertexArray(0);
    }

    private void  bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer = this.storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }


    private IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;

    }

    private FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;

    }
}
