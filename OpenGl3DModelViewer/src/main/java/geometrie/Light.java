package geometrie;

import org.lwjgl.util.vector.Vector3f;

/**
 * Diese Klasse erzeugt ein Punktlicht (Bei uns als Sonne dargestellt).
 */
public class Light {

    private Vector3f position;
    private Vector3f colour;

    /**
     * Erstellt ein Punktlicht aus der übergebenen Position und Farbe.
     *
     * @param position Position des Punktlichts.
     * @param colour Farbe des Punktlichts.
     */
    public Light(Vector3f position, Vector3f colour) {
        this.position = position;
        this.colour = colour;
    }

    /**
     * Gibt die Position des Punktlichts zurück.
     * @return position Position des Punktlichts.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Gibt die Farbe des Punktlichts zurück.
     * @return colour Farbe des Punktlichts.
     */
    public Vector3f getColour() {
        return colour;
    }
}
