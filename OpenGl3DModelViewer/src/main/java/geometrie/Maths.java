package geometrie;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 * Beinhaltet Mathematische-Funktionen zur Erstellung von Transformation- und View-Matrix.
 */
public class Maths {

    /**
     * Erstellt aus der übergebenen Translation, Rotation uns Skalierung eine Transformations-Matrix.
     *
     * @param translation Translation
     * @param rotation Rotation
     * @param scale Skalierung
     * @return Erstellte Transformations-Matrix.
     */
    public static Matrix4f createTransformationMatrix(Vector3f translation, Vector3f rotation, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
        return matrix;
    }

    /**
     * Erstellt eine View-Matrix abhängig von den Werten der übergebenen Kamera (Position, Pitch und Yaw).
     *
     * @param camera Kamera, abhängig von welcher eine View-Matrix erstellt werden soll.
     * @return Erstellte View-Matrix.
     */
    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1,0,0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0,1,0), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f.translate(negativCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }

}
