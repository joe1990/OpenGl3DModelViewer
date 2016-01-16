package renderEngine;

/**
 * Klasse für ein Model, bestehend aus dem Vertex Array Object (VAO) und der Anzahl Knoten des Models.
 */
public class Model {

    private int numberOfVertices;
    private int vaoID;

    /**
     * Konstruktor. Erzeugt eine neues Model für das übergebenen VAO und die Anzahl Knoten.
     *
     * @param voaId ID des VAO.
     * @param numberOfVertices Anzahl Knoten
     */
    public Model(int voaId, int numberOfVertices){
        this.vaoID = voaId;
        this.numberOfVertices = numberOfVertices;
    }

    /**
     * Gibt die ID des Vertex Array Objects (VAO) zurück.
     * @return vaoID ID des VAO.
     */
    public int getVaoId() {
        return vaoID;
    }

    /**
     * Gibt die Anzahl Knoten zurück.
     * @return vertexCount Anzahl Knoten
     */
    public int getNumberOfvertices() {
        return numberOfVertices;
    }

}
