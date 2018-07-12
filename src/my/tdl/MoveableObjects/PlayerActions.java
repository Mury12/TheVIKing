package my.tdl.MoveableObjects;

import my.tdl.generator.World;
import my.tdl.managers.Mousemanager;

/**
 *
 * @author andremury
 */
public class PlayerActions {

    private static boolean spawned;

    public static boolean moving;
    private float maxSpeed = 4 * 32F;
    private float speedUp = 0;
    private float speedDown = 0;
    private float speedLeft = 0;
    private float speedRight = 0;
    private final float slowdown = 4.93F; //quanto de incremento para o ease-in-out
    private final float fixDt = 1F / 60F; //pixels por frames que ele andará

    Mousemanager playerMM = new Mousemanager();

    private static char keyTyped;
    private static boolean chatBox = false;
    public static boolean message_sent = false;
    private static boolean typing = false;
    private boolean isMsgSet = false;
    private String msg = "";

    public boolean isMoving() {
        return this.moving;
    }

    public boolean hasSpawned() {
        return this.spawned;
    }

    public float getSpeed() {
        if (speedUp > 0) {
            return this.speedUp;
        }
        if (speedDown > 0) {
            return this.speedDown;
        }
        if (speedLeft > 0) {
            return this.speedLeft;
        }
        if (speedRight > 0) {
            return this.speedRight;
        }
        return 0;
    }

    public float getSpeed(String direction) {
        float speed = this.speedUp;
        switch (direction.toLowerCase()) {
            case "up":
                speed = this.speedUp;
            case "down":
                speed = this.speedDown;
            case "left":
                speed = this.speedLeft;
            case "right":
                speed = this.speedRight;
        }
        return speed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getSlowdown() {
        return slowdown;
    }

    public boolean isSpawned() {
        return this.spawned;
    }

    public void attackUp() {

    }

    public void attackDown() {

    }

    public void attackRight() {

    }

    public void attackLeft() {

    }

    public void run() {

    }

    public void setMsg(boolean set, String msg) {
        this.isMsgSet = set;
        this.msg = msg;
    }

    private void sendMessage() {
        this.message_sent = true;
        reloadMessage();
    }

    private void reloadMessage() {
        this.message_sent = false;
        this.typing = false;
    }

    private void resetChat() {
        this.message_sent = false;
        this.typing = false;
        this.chatBox = false;
    }

    public boolean isMsgSet() {
        return this.isMsgSet;
    }

    public static boolean isChatting() {
        return chatBox;
    }

    public static boolean isTyping() {
        return typing;
    }

    public static char getKeyTyped() {
        return keyTyped;
    }

    public static void setCharTyped(char keyChar) {
        keyTyped = keyChar;
    }

    String getMsg() {
        return this.msg;
    }

    void setSpeed(String up, float f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
