package renderEngine;

import geometrie.Maths;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import shaders.ShaderCollection;

/**
 * Created by michael on 16.11.2015.
 *
 * Renderlogic
 */
public class Renderer {

    private static final float FOV = 70; //Field of View angle
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    private Matrix4f projectionMatrix;

    /**
     * @param shader
     */
    public Renderer(ShaderCollection shader){
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    /**
     * Prepare
     */
    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0.8f, 0.8f, 0.8f, 1); //Backgroundcolor in RGB
    }

    /**
     * render method for standard models
     * @param entity
     * @param shader
     */
    public void renderTriangles(Entity entity, ShaderCollection shader){
        GL30.glBindVertexArray(entity.getModel().getVaoId());
        GL20.glEnableVertexAttribArray(0); //activate Vertices
        GL20.glEnableVertexAttribArray(1); //activate Normals
        GL20.glEnableVertexAttribArray(2); //activate colors
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(
                entity.getTranslation(),
                new Vector3f(entity.getRotX(), entity.getRotY(), entity.getRotZ()),
                entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
        GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0); //deactivate Vertices
        GL20.glDisableVertexAttribArray(1); //deactivate Normals
        GL20.glDisableVertexAttribArray(2); //deactivate colors
        GL30.glBindVertexArray(0); //deactivate VAO
    }

    /**
     * render method for line models
     * @param entity
     * @param shader
     */
    public void renderLines(Entity entity, ShaderCollection shader) {
        GL30.glBindVertexArray(entity.getModel().getVaoId());
        GL20.glEnableVertexAttribArray(0); //activate Vertices
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(
                entity.getTranslation(),
                new Vector3f(entity.getRotX(), entity.getRotY(), entity.getRotZ()),
                entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
        GL11.glDrawElements(GL11.GL_LINES, entity.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0); //deactivate Vertices
        GL30.glBindVertexArray(0); //deactivate VAO
    }

    /**
     * creates a projection matrix
     */
    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }

}
