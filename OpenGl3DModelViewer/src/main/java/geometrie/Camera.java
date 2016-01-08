package geometrie;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 23.11.2015.
 *
 * Camera class
 */
public class Camera {

    private float dist_Model = 30;
    private float angleAroundModel = 180;
    private Vector3f position = new Vector3f(0,0,0); //Kamera Position
    private float yaw; //rotation um die Y-Achse der Kamera
    private float pitch = 20; //rotation um die X-Achse der Kamera

    /**
     * moves the camera according the user input
     */
    public void move(){

        //Zoom
        float zoomLevel = Mouse.getDWheel() * 0.05f;
        dist_Model -= zoomLevel;

        //pitch
        if(Mouse.isButtonDown(0)){
            float pitchChange = Mouse.getDY() * 0.2f;
            pitch -= pitchChange;
        }

        //angle around Model
        if(Mouse.isButtonDown(0)){
            float angleChange = Mouse.getDX() * 0.2f;
            angleAroundModel -= angleChange;
        }

        //distance
        float dist_horizontal =  dist_Model * (float)Math.cos(Math.toRadians(pitch));
        float dist_vertical = dist_Model * (float)Math.sin(Math.toRadians(pitch));

        //position
        float xPos = dist_horizontal * (float)Math.sin(Math.toRadians(angleAroundModel));
        float zPos = dist_horizontal * (float)Math.cos(Math.toRadians(angleAroundModel));

        position.x = - xPos;
        position.z = - zPos;
        position.y = dist_vertical;

        this.yaw = 180 - angleAroundModel;
    }

    /**
     * returns the camera position
     * @return
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * returns the yaw
     * @return
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * returns the pitch
     * @return
     */
    public float getPitch() {
        return pitch;
    }
}
