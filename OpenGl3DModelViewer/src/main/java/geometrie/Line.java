package geometrie;

/**
 * Created by michael on 23.11.2015.
 *
 * Simple lines
 */
public class Line {

    /**
     * @return vertices
     */
    public static float[] getVertices() {

        float[] vertices = {
                0, 0, 0,
                1, 0, 0,
        };
        return vertices;
    }

    /**
     * returns the lines indices
     * @return indices
     */
    public static int[] getIndices() {

        int[] indices = {
                0, 1
        };
        return indices;
    }
}
