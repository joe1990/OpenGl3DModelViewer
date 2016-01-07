package renderEngine;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 14.12.2015.
 *
 * Entity class
 */
public class Entity {

    private RawModel model;
    private Vector3f translation;
    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;

    /**
     * @param model
     * @param translation
     * @param rotX
     * @param rotY
     * @param rotZ
     * @param scale
     */
    public Entity(RawModel model, Vector3f translation, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.translation = translation;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    /**
     * @return model
     */
    public RawModel getModel() {
        return model;
    }

    /**
     * @return translation
     */
    public Vector3f getTranslation() {
        return translation;
    }

    /**
     * @return x rotation
     */
    public float getRotX() {
        return rotX;
    }

    /**
     * @return y rotation
     */
    public float getRotY() {
        return rotY;
    }

    /**
     * @return z rotation
     */
    public float getRotZ() {
        return rotZ;
    }

    /**
     * @return scale factor
     */
    public float getScale() {
        return scale;
    }

}
