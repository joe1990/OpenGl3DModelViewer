package renderEngine;

import org.lwjgl.util.vector.Vector3f;

/**
 * Klasse, welche eine Entit�t (Einheit) in unserer Applikation darstellt.
 * Eine Entit�t ist beispielsweise die Sonne, oder eine Linie des Gitternetzes.
 */
public class Entity {

    private Vector3f rotation;
    private Vector3f translation;
    private float scale;

    private Model model;

    /**
     * Konstruktor. Erzeugt eine neue Entit�t f�r die �bergebenen Parameter.
     */
    public Entity(Model model, Vector3f translation, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.translation = translation;
        this.rotation = new Vector3f(rotX, rotY, rotZ);
        this.scale = scale;
    }

    /**
     * Gibt das Model der Entit�t zur�ck.
     * @return model Model der Entit�t.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Gibt die Translation der Entit�t zur�ck.
     * @return translation Translation der Entit�t.
     */
    public Vector3f getTranslation() {
        return translation;
    }

    /**
     * Gibt die Rotation der Entit�t um die X-Achse zur�ck.
     * @return Rotation der Entit�t um die X-Achse.
     */
    public float getRotX() {
        return rotation.x;
    }

    /**
     * Gibt die Rotation der Entit�t um die Y-Achse zur�ck.
     * @return Rotation der Entit�t um die Y-Achse.
     */
    public float getRotY() {
        return rotation.y;
    }

    /**
     * Gibt die Rotation der Entit�t um die Z-Achse zur�ck.
     * @return Rotation der Entit�t um die Z-Achse.
     */
    public float getRotZ() {
        return rotation.z;
    }

    /**
     * Gibt den Skalierungsfaktor der Entit�t zur�ck.
     * @return Skalierungsfaktor der Entit�t.
     */
    public float getScale() {
        return scale;
    }

}
