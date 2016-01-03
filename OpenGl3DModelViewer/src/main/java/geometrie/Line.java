package geometrie;

/**
 * Created by michael on 23.11.2015.
 */
public class Line {

    public static float[] getVertices() {

        float[] vertices = {
                0.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
        };
        return vertices;
    }


    public static int[] getIndices() {

        int[] indices = {
                0, 1
        };
        return indices;
    }
}
