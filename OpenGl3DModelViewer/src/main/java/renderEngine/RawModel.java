package renderEngine;

/**
 * Created by michael on 16.11.2015.
 */
public class RawModel {

    private int vaoID;
    private int vertexCount;

    /**
     * @param voaId
     * @param vertexCount
     */
    public RawModel(int voaId, int vertexCount){
        this.vaoID = voaId;
        this.vertexCount = vertexCount;
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
    public int getVertexCount() {
        return vertexCount;
    }

}
