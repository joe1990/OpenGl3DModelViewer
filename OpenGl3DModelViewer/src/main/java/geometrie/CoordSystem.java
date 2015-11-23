package geometrie;

/**
 * Created by michael on 23.11.2015.
 */
public class CoordSystem {

    float[] vertices;
    float[] normals;
    int[] indices;


    public CoordSystem() {

        float[] vertices = {
                0.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f
        };
        this.vertices = vertices;

        float[] normals = {

        };
        this.normals = normals;

        int[] indices = {
                0, 1,
                0, 2,
                0, 3
        };
        this.indices = indices;

    }


    public float[] getNormals() {
        return normals;
    }


    public float[] getVertices() {
        return vertices;
    }


    public int[] getIndices() {
        return indices;
    }


}
