package renderEngine;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 14.12.2015.
 */
public class Entity {

    private RawModel model;
    private Vector3f translation;
    private float rotX;
    private float rotY;
    private float rotZ;
    private float scale;

    public Entity(RawModel model, Vector3f translation, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.translation = translation;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public RawModel getModel() {
        return model;
    }

    public void setModel(RawModel model) {
        this.model = model;
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
