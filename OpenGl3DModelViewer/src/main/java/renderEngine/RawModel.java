package renderEngine;

/**
 * Created by michael on 16.11.2015.
 */
public class RawModel {

    private int vaoId;
    private int vertexCount;

    public RawModel(int voaId, int vertexCount){
        this.vaoId = voaId;
        this.vertexCount = vertexCount;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
