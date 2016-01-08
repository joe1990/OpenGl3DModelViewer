package renderEngine;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 14.12.2015.
 *
 * Entity class
 */
public class Entity {

    Vector3f rotation;
    Vector3f translation;
    float scale;

    Model model;

    /**
     * @param model
     * @param translation
     * @param rotX
     * @param rotY
     * @param rotZ
     * @param scale
     */
    public Entity(Model model, Vector3f translation, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.translation = translation;
        this.rotation = new Vector3f(rotX, rotY, rotZ);
        this.scale = scale;
    }

    /**
     * @return model
     */
    public Model getModel() {
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
        return rotation.x;
    }

    /**
     * @return y rotation
     */
    public float getRotY() {
        return rotation.y;
    }

    /**
     * @return z rotation
     */
    public float getRotZ() {
        return rotation.z;
    }

    /**
     * @return scale factor
     */
    public float getScale() {
        return scale;
    }

}
