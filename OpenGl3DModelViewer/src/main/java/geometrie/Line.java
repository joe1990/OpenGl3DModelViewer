package geometrie;

/**
 * Created by michael on 23.11.2015.
 */
public class Line {

    float[] vertices = new float[6];

    public Line(float fromX, float fromZ, float toX, float toZ){
        vertices[0] = fromX;
        vertices[1] = 0.0f;
        vertices[2] = fromZ;
        vertices[3] = toX;
        vertices[4] = 0.0f;
        vertices[5] = toZ;
    }

    public float[] getVertices() {
        return vertices;
    }


    public int[] getIndices() {
        int[] indices = {
            0,1
        };
        return indices;
    }
}
