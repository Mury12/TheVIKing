package my.tdl.MoveableObjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import my.project.gop.main.Vector2F;
import my.tdl.generator.MessageBaloon;
import my.tdl.managers.Mousemanager;

/**
 *
 * @author andremury
 */
public class PlayerActions {
    
    private final Player p;
    private boolean spawned;
    
    public static boolean moving, running, duck;
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
//
        //realiza a renderização em tempo real apenas dos bloccks no campo
        p.getPlayerAnimations().setRender(new Rectangle(
                (int) (pos.xpos - pos.getWorldLocation().xpos
                - p.getPlayerAnimations().getRenderDistanceW() / 2 + p.getPlayerAnimations().getWidth() / 2),
                (int) (pos.ypos - pos.getWorldLocation().ypos
                - p.getPlayerAnimations().getRenderDistanceH() / 2 + p.getPlayerAnimations().getHeight() / 2),
                p.getPlayerAnimations().getRenderDistanceW() * 48,
                p.getPlayerAnimations().getRenderDistanceH() * 48)
        );
        goRun();
        p.getPlayerAnimations().setMoveAmounts();
        p.getPlayerAnimations().setPlayerFigure();
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public void setMovingState(boolean b) {
        moving = b;
    }
    
    public void setRunState(boolean b) {
        running = b;
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public boolean isDuck() {
        return duck;
    }
    
    public void goRun() {
        if (isRunning()) {
            setMaxSpeed(194);
        } else {
            setMaxSpeed(128);
        }
        
        if (!p.isTired()) {
            if (isMoving() && isRunning()) {
                p.drawStamina();
            }
        } else {
            if (p.getStamin() >= 50) {
                p.setTired(false);
            }
            setRunState(false);
        }
        
        if (p.getStamin() <= 0) {
            p.setTired(true);
        }
        
        if (p.isTired() && p.getStamin() <= 0) {
            setMsg(true, p.randomStaminAlert());
        } else if (!p.isTired() && p.getStamin() >= 20) {
            setMsg(false, " ");
        }
        
        p.recoverStamina(0.1);
        
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
                break;
            case "down":
                speed = this.speedDown;
                break;
            case "left":
                speed = this.speedLeft;
                break;
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
    
    public void spawn(boolean b) {
        this.spawned = b;
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
    
}
