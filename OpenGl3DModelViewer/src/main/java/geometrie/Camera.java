package geometrie;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Entity;

/**
 * Created by michael on 23.11.2015.
 */
public class Camera {

    private  float distanceToModel = 50;
    private float angleAroundModel = 0;


    private  Entity entity;

        public Vector3f position = new Vector3f(0,0,0); //Kamera Position
        public float yaw; //rotation um die Y-Achse der Kamera
        public float pitch; //rotation um die X-Achse der Kamera
        public float roll; //rotation um die Z-Achse der Kamera


        public Camera(Entity entity){
            this.entity = entity;
        }

        public void move(){

            /*
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
            */
            calculateZoom();
            calculatePitch();
            calculateAngleAroundModel();

            float horizontalDistance = calculateHorizontalDistance();
            float verticalDistance = calculateVerticalDistance();

            calculateCameraPosition(horizontalDistance, verticalDistance);
            this.yaw = 180 - (entity.getRotY() + angleAroundModel);
        }

        private void calculateCameraPosition(float horizontalDistance, float verticalDistance ){
            float theta = entity.getRotY() + angleAroundModel;
            float offsetX = horizontalDistance * (float)Math.sin(Math.toRadians(theta));
            float offsetZ = horizontalDistance * (float)Math.cos(Math.toRadians(theta));
            position.x = entity.getTranslation().x - offsetX;
            position.z = entity.getTranslation().z - offsetZ;
            position.y = entity.getTranslation().y + verticalDistance;
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
