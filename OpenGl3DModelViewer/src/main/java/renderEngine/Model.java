package renderEngine;

/**
 * Created by michael on 16.11.2015.
 */
public class Model {

    private int vertices;
    private int vaoID;

    /**
     * @param voaId
     * @param vertices
     */
    public Model(int voaId, int vertices){
        this.vaoID = voaId;
        this.vertices = vertices;
    }

    /**
     * @return vaoID
     */
    public int getVaoId() {
        return vaoID;
    }

    /**
     * @return vertexCount
     */
    public int getNumberOfvertices() {
        return vertices;
    }

}
