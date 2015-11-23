package geometrie;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 23.11.2015.
 */
public class Camera {

    private  float distanceToModel = 50;
    private float angleAroundModel = 0;


        //3d vector to store the camera's position in
        public Vector3f position = new Vector3f(0,0,0);
        //the rotation around the Y axis of the camera
        public float yaw;
        //the rotation around the X axis of the camera
        public float pitch; //Rotation x,y,z
        public float roll;


        public Camera(){
            calculateZoom();
            calculatePitch();
            calculateAngleAroundModel();
            float horizontalDistance = calculateHorizontalDistance();
            float verticalDistance = calculateVerticalDistance();
            //calculateCameraPosition(horizontalDistance, verticalDistance);
        }

        public void move(){

            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                position.z -= 0.2f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                position.x += 0.2f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A)){
                position.x -= 0.2f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                position.z += 0.2f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
                position.y += 0.2f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_Y)){
                position.y -= 0.2f;
            }
            calculateZoom();
        }

        private void calculateCameraPosition(float thorizontalDistance, float verticalDistance ){
            float theta = angleAroundModel;
            float offsetX = thorizontalDistance * (float) Math.sin(Math.toRadians(theta));
            float offsetZ = thorizontalDistance * (float)Math.cos(Math.toRadians(theta));
            position.x = -offsetX;
            position.z = -offsetZ;
            position.y = verticalDistance;
        }

        private float calculateHorizontalDistance(){
            return distanceToModel * (float)Math.cos(Math.toRadians(pitch));
        }

        private float calculateVerticalDistance() {
            return distanceToModel * (float)Math.sin(Math.toRadians(pitch));
        }

        private void calculateZoom(){
            position.z -= Mouse.getDWheel() * 0.01f;
            float zoomLevel = Mouse.getDWheel() * 0.1f;
            distanceToModel -= zoomLevel;
        }

        private void calculatePitch(){
            if(Mouse.isButtonDown(1)){
                float pitchChange = Mouse.getDY() * 0.1f;
                pitch -= pitchChange;
            }
        }

        private void calculateAngleAroundModel(){
            if(Mouse.isButtonDown(0)){
                float angleChange = Mouse.getDX() * 0.3f;
                angleAroundModel -= angleChange;
            }
        }

        public Vector3f getPosition() {
            return position;
        }

        public float getYaw() {
            return yaw;
        }

        public float getPitch() {
            return pitch;
        }

        public float getRoll() {
            return roll;
        }

        public void setPitch(float pitch) {
            this.pitch = pitch;
        }

        public void setYaw(float yaw) {
            this.yaw = yaw;
        }

}
