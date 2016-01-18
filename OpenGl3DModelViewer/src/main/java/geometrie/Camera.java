package geometrie;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Diese Klasse ist f�r alle Operationen betreffend der Kamera zust�ndig.
 * Sie beinhaltet eine Methode zum Verschieben der Kamera-Position und auch Methoden um Kameraeigenschaften abzufragen
 * (z.B. Position, Pitch oder Yaw)
 * Pitch: Rotation um die X-Achse der Kamera
 * Yaw: Rotation um die Y-Achse der Kamera
 */
public class Camera {

    private float distanceModel = 60;
    private float angleAroundModel = 180;
    private Vector3f position = new Vector3f(0,0,0); //Kamera Position
    private float yaw; //Rotation um die Y-Achse der Kamera
    private float pitch = 30; //Rotation um die X-Achse der Kamera

    /**
     * Verschiebt die Kamera abh�ngig vom Input des Users, d.h. abh�ngig von der Drehung am Mausrad und auch abh�ngig
     * von der Rechts-, Links-, Oben-, Unten-Verschiebung.
     */
    public void move(){

        //Zoom
        float zoomLevel = Mouse.getDWheel() * 0.05f;
        distanceModel -= zoomLevel;

        //Pitch (Rotation um die X-Achse)
        if(Mouse.isButtonDown(0)){
            float pitchChange = Mouse.getDY() * 0.2f;
            pitch -= pitchChange;
        }

        //angle around Model
        if(Mouse.isButtonDown(0)){
            float angleChange = Mouse.getDX() * 0.2f;
            angleAroundModel -= angleChange;
        }

        //Distanz
        float distanceHorizontal =  distanceModel * (float)Math.cos(Math.toRadians(pitch));
        float distanceVertical = distanceModel * (float)Math.sin(Math.toRadians(pitch));

        //Position
        float xPos = distanceHorizontal * (float)Math.sin(Math.toRadians(angleAroundModel));
        float zPos = distanceHorizontal * (float)Math.cos(Math.toRadians(angleAroundModel));

        position.x = - xPos;
        position.z = - zPos;
        position.y = distanceVertical;

        this.yaw = 180 - angleAroundModel;
    }

    /**
     * Gibt die Kamera-Position zur�ck.
     *
     * @return Kamera-Position.
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Gibt den Yaw-Wert (Rotation um die Y-Achse der Kamera) zur�ck.
     *
     * @return Aktueller Yaw-Wert.
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * Gibt den Pitch Wert (Rotation um die X-Achse der Kamera) zur�ck.
     *
     * @return Aktueller Pitch-Wert.
     */
    public float getPitch() {
        return pitch;
    }
}
