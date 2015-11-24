package geometrie;

/**
 * Created by michael on 23.11.2015.
 */
public class CoordSystem {

    public static float[] getVertices() {

        float[] vertices = {
                0.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f
        };
        return vertices;
    }


    public static int[] getIndices() {

        int[] indices = {
                0, 1,
                0, 2,
                0, 3
        };
        return indices;
    }


}
