package geometrie;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 23.11.2015.
 *
 * Camera class
 */
public class Camera {

    private float distanceToModel = 30;
    private float angleAroundModel = 180;
    private Vector3f position = new Vector3f(0,0,0); //Kamera Position
    private float yaw; //rotation um die Y-Achse der Kamera
    private float pitch = 20; //rotation um die X-Achse der Kamera

    /**
     * moves the camera according the user input
     */
    public void move(){
        calculateZoom();
        calculatePitch();
        calculateAngleAroundModel();
        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();

        calculateCameraPosition(horizontalDistance, verticalDistance);
        this.yaw = 180 - angleAroundModel;
    }

    /**
     * calculates the camera position
     * @param horizontalDistance
     * @param verticalDistance
     */
    private void calculateCameraPosition(float horizontalDistance, float verticalDistance ){
        float offsetX = horizontalDistance * (float)Math.sin(Math.toRadians(angleAroundModel));
        float offsetZ = horizontalDistance * (float)Math.cos(Math.toRadians(angleAroundModel));
        position.x = - offsetX;
        position.z = - offsetZ;
        position.y = verticalDistance;
    }

    /**
     * calculates the horizontal distance
     * @return
     */
    private float calculateHorizontalDistance(){
        return distanceToModel * (float)Math.cos(Math.toRadians(pitch));
    }

    /**
     * calculates the vertical distance
     * @return
     */
    private float calculateVerticalDistance() {
        return distanceToModel * (float)Math.sin(Math.toRadians(pitch));
    }

    /**
     * calculate the zoom factor
     */
    private void calculateZoom(){
        float zoomLevel = Mouse.getDWheel() * 0.05f;
        distanceToModel -= zoomLevel;
    }

    /**
     * calculates the pitch (x-axis)
     */
    private void calculatePitch(){
        if(Mouse.isButtonDown(0)){
            float pitchChange = Mouse.getDY() * 0.2f;
            pitch -= pitchChange;
        }
    }

    /**
     * calculates the angle around the model
     */
    private void calculateAngleAroundModel(){
        if(Mouse.isButtonDown(0)){
            float angleChange = Mouse.getDX() * 0.2f;
            angleAroundModel -= angleChange;
        }
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
