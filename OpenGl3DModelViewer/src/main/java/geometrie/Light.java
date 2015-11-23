package geometrie;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 23.11.2015.
 */
public class Light {

        Vector3f position;
        Vector3f colour;

        public Light(Vector3f position, Vector3f colour) {
            this.position = position;
            this.colour = colour;
        }

        public Vector3f getPosition() {
            return position;
        }

        public void setPosition(Vector3f position) {
            this.position = position;
        }

        public Vector3f getColour() {
            return colour;
        }

        public void setColour(Vector3f colour) {
            this.colour = colour;
        }


    public float[] getVertices() {

        float x =  position.getX();
        float y =  position.getY();
        float z =  position.getZ();

        float[] vertices = {

                x, y, z,
                x, y, z
        };

        return vertices;
    }


    public int[] getIndices() {

        int[] indices = {
            0,1
        };

        return indices;
    }


}
