package geometrie;

/**
 * Klasse welche eine einfache Linie darstellt.
 */
public class Line {

    /**
     * gibt die Knoten der Linie in einem Array zurück.
     * @return Knoten der Linie.
     */
    public static float[] getVertices() {
        float[] vertices = {
                0, 0, 0,
                1, 0, 0,
        };
        return vertices;
    }

    /**
     * Gibt die Indizies der Linie (0 und 1) als Knoten zurück
     * @return Indizies der Linie (0 und 1)
     */
    public static int[] getIndices() {
        int[] indices = {
                0, 1
        };
        return indices;
    }
}
