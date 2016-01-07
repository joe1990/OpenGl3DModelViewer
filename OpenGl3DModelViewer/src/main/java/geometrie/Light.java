package geometrie;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 23.11.2015.
 *
 * light class - creates a pointlight
 */
public class Light {

        private Vector3f position;
        private Vector3f colour;

    /**
     * default constructor
     * @param position
     * @param colour
     */
    public Light(Vector3f position, Vector3f colour) {
        this.position = position;
        this.colour = colour;
    }

    /**
     * @return position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * @return colour
     */
    public Vector3f getColour() {
        return colour;
    }
}
