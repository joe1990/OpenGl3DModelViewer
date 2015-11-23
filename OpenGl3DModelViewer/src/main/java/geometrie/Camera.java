package geometrie;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 * Created by michael on 23.11.2015.
 */
public class Camera {


        //3d vector to store the camera's position in
        public Vector3f position = new Vector3f(0,0,0);
        //the rotation around the Y axis of the camera
        public float yaw;
        //the rotation around the X axis of the camera
        public float pitch; //Rotation x,y,z
        public float roll;


        public Camera(){
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
