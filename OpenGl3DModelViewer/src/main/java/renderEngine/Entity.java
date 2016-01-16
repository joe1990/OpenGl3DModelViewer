package renderEngine;

import org.lwjgl.util.vector.Vector3f;

/**
 * Klasse, welche eine Entität (Einheit) in unserer Applikation darstellt.
 * Eine Entität ist beispielsweise die Sonne, oder eine Linie des Gitternetzes.
 */
public class Entity {

    private Vector3f rotation;
    private Vector3f translation;
    private float scale;

    private Model model;

    /**
     * Konstruktor. Erzeugt eine neue Entität für die übergebenen Parameter.
     */
    public Entity(Model model, Vector3f translation, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.translation = translation;
        this.rotation = new Vector3f(rotX, rotY, rotZ);
        this.scale = scale;
    }

    /**
     * Gibt das Model der Entität zurück.
     * @return model Model der Entität.
     */
    public Model getModel() {
        return model;
    }

    /**
     * Gibt die Translation der Entität zurück.
     * @return translation Translation der Entität.
     */
    public Vector3f getTranslation() {
        return translation;
    }

    /**
     * Gibt die Rotation der Entität um die X-Achse zurück.
     * @return Rotation der Entität um die X-Achse.
     */
    public float getRotX() {
        return rotation.x;
    }

    /**
     * Gibt die Rotation der Entität um die Y-Achse zurück.
     * @return Rotation der Entität um die Y-Achse.
     */
    public float getRotY() {
        return rotation.y;
    }

    /**
     * Gibt die Rotation der Entität um die Z-Achse zurück.
     * @return Rotation der Entität um die Z-Achse.
     */
    public float getRotZ() {
        return rotation.z;
    }

    /**
     * Gibt den Skalierungsfaktor der Entität zurück.
     * @return Skalierungsfaktor der Entität.
     */
    public float getScale() {
        return scale;
    }

}
