package geometrie;

/**
 * Created by michael on 23.11.2015.
 *
 * CoordSystem class - for testing purposes only
 */
public class CoordSystem {

    /**
     * returns the vertices
     * @return
     */
    public static float[] getVertices() {

        float[] vertices = {
                0.0f, 0.0f, 0.0f,
                10.0f, 0.0f, 0.0f,
                0.0f, 10.0f, 0.0f,
                0.0f, 0.0f, 10.0f
        };
        return vertices;
    }

    /**
     * returns the indices
     * @return
     */
    public static int[] getIndices() {

        int[] indices = {
                0, 1,
                0, 2,
                0, 3
        };
        return indices;
    }


}
