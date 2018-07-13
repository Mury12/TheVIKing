package my.tdl.MoveableObjects;

import java.awt.Rectangle;
import my.project.gop.main.Vector2F;
import my.tdl.generator.World;
import my.tdl.managers.Mousemanager;

/**
 *
 * @author andremury
 */
public class PlayerActions {

    private final Player p;
    private static boolean spawned;

    public boolean moving;
    private float maxSpeed = 4 * 32F;
    public float speedUp = 0;
    public float speedDown = 0;
    public float speedLeft = 0;
    public float speedRight = 0;
    private float slowdown = 4.93F; //quanto de incremento para o ease-in-out
    public final float fixDt = 1F / 60F; //pixels por frames que ele andará

    Mousemanager playerMM = new Mousemanager();

    private char keyTyped;
    public boolean chatBox = false;
    public boolean message_sent = false;
    public boolean typing = false;
    private boolean isMsgSet = false;
    private String msg = "";
    private Vector2F pos;

    public PlayerActions(Vector2F pos, Player p) {
        this.pos = pos;
        this.p = p;
    }

    public void tick(double deltaTime) {

        playerMM.tick();

        //realiza a renderização em tempo real apenas dos bloccks no campo
        p.getPlayerAnimations().setRender(new Rectangle(
                (int) (pos.xpos - pos.getWorldLocation().xpos
                - p.getPlayerAnimations().getRenderDistanceW() / 2 + p.getPlayerAnimations().getWidth() / 2),
                (int) (pos.ypos - pos.getWorldLocation().ypos
                - p.getPlayerAnimations().getRenderDistanceH() / 2 + p.getPlayerAnimations().getHeight() / 2),
                p.getPlayerAnimations().getRenderDistanceW() * 48,
                p.getPlayerAnimations().getRenderDistanceH() * 48)
        );
    }

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

    public void setSpeed(String dir, float spd) {
        switch (dir.toLowerCase()) {
            case "up":
                this.speedUp = spd;
                break;
            case "down":
                this.speedDown = spd;
                break;
            case "left":
                this.speedLeft = spd;
                break;
            case "right":
                this.speedRight = spd;
                break;
            default:
        }
    }

    public float getSpeed(String direction) {
        float speed = 0;
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

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
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

    void resetChat() {
        this.message_sent = false;
        this.typing = false;
        this.chatBox = false;
    }

    public boolean isMsgSet() {
        return this.isMsgSet;
    }

    public boolean isChatting() {
        return chatBox;
    }

    public boolean isTyping() {
        return typing;
    }

    public char getKeyTyped() {
        return keyTyped;
    }

    public void setCharTyped(char keyChar) {
        keyTyped = keyChar;
    }

    String getMsg() {
        return this.msg;
    }

    void setMoving(boolean b) {
        this.moving = b;
    }

}
