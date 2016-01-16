package renderEngine;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse, welche für das Lesen von Wavefront OBJ-Files zuständig ist.
 *
 * Die Klasse aus folgender Quelle wurde auf die eigenen Bedürfnisse angepasst:
 * http://jogamp.org/jogl-demos/src/demos/util/ObjReader.java
 */
public class OBJReader {

    /**
     * Lädt das 3D-Model aus dem übergebenen Wavefront-OBJ-File und gibt dieses zurück.
     *
     * @param file File-Objekt des Wavefront OBJ-Files, welches gelesen werden soll.
     * @param loader Objekt, welches für das Laden von Daten in die Grafikkarte zuständig ist.
     * @param color Farbe des 3D-Models.
     * @return 3D-Model aus dem übergebenen Wavefront-OBJ-File.
     */
    public static Model loadObjModel(File file, GPUInterface loader, Color color) {
        FileReader fileReader = null;

        //Gibt auf der Konsole eine Fehlermeldung aus, wenn das OBJ-File nicht gelesen werden konnte.
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load file!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Couldn't load file!");
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fileReader);

        String lineOfFile;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();

        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        int[] indicesArray = null;

        try {
            //Liest das Wavefront-OBJ-File Zeilenweise ein.
            while (true) {
                lineOfFile = reader.readLine();
                String[] currentLine = lineOfFile.split(" ");

                //Wenn Zeile des OBJ-Files mit v beginnt, dann handelt es sich um die Koordinaten eines Punktes (Vertex)
                if (lineOfFile.startsWith("v ")) {
                    Vector3f vertex;
                    if(currentLine[1].equals("")){
                        vertex = new Vector3f(Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]), Float.parseFloat(currentLine[4]));
                    }else{
                        vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    }
                    vertices.add(vertex);
                }
                //Wenn Zeile des OBJ-Files mit vt beginnt, dann handelt es sich um einen Texturkoordinatenpunkt.
                //Diese Angabe wird von uns später nicht verarbeitet.
                else if (lineOfFile.startsWith("vt ")) {
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                }
                //Wenn Zeile mit vn beginnt, handelt es sich um einen Normalenvektor
                else if (lineOfFile.startsWith("vn ")) {
                    Vector3f normal;
                    if(currentLine[1].equals("")){
                        normal = new Vector3f(Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]), Float.parseFloat(currentLine[4]));
                    }else{
                        normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]), Float.parseFloat(currentLine[3]));
                    }
                    normals.add(normal);
                }
                //Wenn Zeile mit f beginnt, handelt es sich um ein Face Triangle
                else if (lineOfFile.startsWith("f ")) {
                    textureArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }

            //Face_lines: Vertex_1/Textur_1/Normal_1
            while (lineOfFile != null) {
                if (!lineOfFile.startsWith("f ")) {
                    lineOfFile = reader.readLine();
                    continue;
                }
                String[] currentLine = lineOfFile.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");

                processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
                processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);
                lineOfFile = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Konvertiere in Float-Array
        verticesArray = new float[vertices.size() * 3];
        indicesArray = new int[indices.size()];

        int vertexPointer = 0;
        for (Vector3f vertex : vertices) {
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }

        for (int i = 0; i < indices.size(); i++) {
            indicesArray[i] = indices.get(i);
        }

        return loader.loadVAO(verticesArray, indicesArray, normalsArray, color);
    }

    private static void processVertex(String[] vertexData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalsArray) {
        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indices.add(currentVertexPointer);
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalsArray[currentVertexPointer * 3] = currentNorm.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
        normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
    }
}

