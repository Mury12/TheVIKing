package my.tdl.MoveableObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import my.project.gop.main.Vector2F;
import my.project.gop.main.loadImageFrom;
import my.tdl.gamestates.DungeonLevelLoader;
import my.tdl.generator.ChatBox;
import my.tdl.main.Check;
import my.tdl.main.Main;
import my.tdl.managers.GUImanager;
import my.tdl.managers.HUDmanager;
import my.tdl.generator.World;

/**
 *
 * @author Andre
 */
public class Player implements KeyListener {

    private World world;
    private static boolean debug = false;

    //velocidade do jogador para os lados.
    private PlayerActions playerAct;
    private PlayerAnimations playerAni;
    private final BufferedImage msg_bgbody = loadImageFrom.LoadImageFrom(Main.class, "msg_bgbody.png");
    private final BufferedImage msg_bgtail = loadImageFrom.LoadImageFrom(Main.class, "msg_tailbg.png");
    private final BufferedImage msg_bgright = loadImageFrom.LoadImageFrom(Main.class, "msg_bgright.png");

    public double stamina = 100;
    private int playerLevel = 1;
    private double lifePoints = 105;

    public Check chk = new Check();
    private boolean tired;

    private HUDmanager hudm;
    private GUImanager guim;
    private final Vector2F pos;
    public static int kp;

    public Player() {
        pos = new Vector2F(Main.width / 2 - PlayerAnimations.width / 2, Main.height / 2 - PlayerAnimations.height / 2); //define o player exatamente no meio da tela
        playerAct = new PlayerActions(pos, this);
        playerAni = new PlayerAnimations(pos, this);
    }

    public void init(World world) {
        hudm = new HUDmanager(world);
        guim = new GUImanager(this);
        this.world = world;
        playerAni.setWorld(world);
        //inicia a renderização 
        playerAni.setRender(new Rectangle(
                (int) (playerAni.pos.xpos - playerAni.pos.getWorldLocation().xpos + playerAni.pos.xpos - playerAni.getRenderDistanceW() / 2 + playerAni.getWidth() / 2),
                (int) (playerAni.pos.ypos - playerAni.pos.getWorldLocation().ypos + playerAni.pos.ypos - playerAni.getRenderDistanceH() / 2 + playerAni.getHeight() / 2),
                playerAni.getRenderDistanceW() * 48,
                playerAni.getRenderDistanceH() * 48)
        );
        
        playerAni.animatePlayer();
        playerAct.spawn(true);
    }

    //O MÉTODO TICK É RESPONSÁVEL POR ATUALIZAR AS INFORMAÇÕES PROCESSADAS NO GAME
    //sistema de cansaço
    public String randomStaminAlert() {
        String msg = "Sorry, I can't do anything right now.";
        Random rand = new Random();
        switch (rand.nextInt(4)) {
            case 1:
                msg = "I'd be glad if you stop running now..";
                break;
            case 2:
                msg = "Wow! That was really tiring!";
                break;
            case 3:
                msg = "My stamin is out, I can't run.";
                break;
            case 4:
                msg = "PLEASE BABY! LET ME REST A LITTLE!!";
                break;
        }
        return msg;
    }

    /**
     * ***************************RENDE
     *
     ****************************
     * @param g
     */
    public void render(Graphics2D g) {
        //g.fillRect((int) pos.xpos, (int) pos.ypos, width, height);

        //g.clipRect(0, 0,Main.width, Main.height);
        //UP
        //Representação da área de renderização efetiva. //drawRect(posicao do eixo X na tela, posicao eixo Y na tela, largura, altura)
        //g.drawRect((int)pos.xpos - renderDistanceW*32/2 + width / 2, (int)pos.ypos - renderDistanceH*32 / 2 + height / 2, renderDistanceW * 32, renderDistanceH * 32);
        playerAni.drawAnimation(g);
        hudm.render(g);
        playerAni.drawLifeBar(g);
        playerAni.drawStaminBar(g);
        g.setColor(Color.WHITE);
        g.drawString("Level: " + getPlayerLevel(), 11, Main.height - 40);
        guim.render(g);
        playerAct.playerMM.render(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public int getKp() {
        return kp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //pega o código da tecla pressionada
        kp = key;
        if (key == KeyEvent.VK_UP) {
            if (!playerAct.isMoving()) {
                playerAct.setMovingState(true);
            }
            PlayerAnimations.up = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            if (!playerAct.isMoving()) {
                playerAct.setMovingState(true);
            }
            PlayerAnimations.down = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            if (!playerAct.isMoving()) {
                playerAct.setMovingState(true);
            }
            PlayerAnimations.left = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            if (!playerAct.isMoving()) {
                playerAct.setMovingState(true);
            }
            PlayerAnimations.right = true;
        }
        if (key == KeyEvent.VK_SHIFT) {
            playerAct.setRunState(true);
        }
        if (key == KeyEvent.VK_CONTROL) {
            PlayerActions.duck = true;
        }

        if (playerAct.isChatting()) {
            if (playerAct.isTyping()) {
                if (key == 8) {
                    ChatBox.backspace = true;
                }
                if (key >= 32 && key <= 122) {
                    if (key != KeyEvent.VK_UP && key != KeyEvent.VK_DOWN && key != KeyEvent.VK_LEFT && key != KeyEvent.VK_RIGHT) {
                        ChatBox.typed = true;
                    }
                    playerAct.setCharTyped(e.getKeyChar());
                    ChatBox.space = (key == 32);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            PlayerAnimations.up = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            PlayerAnimations.down = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            PlayerAnimations.left = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            PlayerAnimations.right = false;
        }
        if (key == KeyEvent.VK_SHIFT) {
            playerAct.setRunState(false);
        }
        if (key == KeyEvent.VK_CONTROL) {
            PlayerActions.duck = false;
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
        if (key == KeyEvent.VK_F3) {
            debug = !isDebugging();
        }
        if (key == KeyEvent.VK_F12) {
            setPlayerLevel(1);
            System.out.println(getPlayerLevel());
        }
        if (key == KeyEvent.VK_P) {
            DungeonLevelLoader.world.changeLevel("World", "Map2");
        }
        if (key == KeyEvent.VK_ENTER) {
            if (!playerAct.isChatting()) {
                playerAct.chatBox = true;
                System.out.println("Chat opened.");
            } else {
                if (playerAct.isTyping()) {
                    playerAct.message_sent = true;
                    ChatBox._message = ChatBox.message;
                    if (!ChatBox.chatLog.contains(ChatBox.message)) {
                        if (ChatBox.chatLog.size() * 13.5 >= Main.height / 3 - 33) {
                            ChatBox.chatLog.remove(0);
                        }
                        ChatBox.chatLog.add(ChatBox.message);
                    }
                    System.out.println("Message sent.");
                } else {
                    if (playerAct.isChatting() && !playerAct.isTyping()) {
                        playerAct.typing = true;
                        System.out.println("Ready to type");
                    }
                }
            }
        }
        if (key == KeyEvent.VK_F2) {
            if (playerAct.isChatting()) {
                playerAct.resetChat();
                System.out.println("Chat closed.");
            }
        }
    }
    public void recoverStamina(double amount) {
        if (getStamin() < 100) {
            setStamin(getStamin() + amount);
        }
        if (getStamin() >= 50) {
            setTired(false);
            getPlayerActions().setMsg(false, " ");

        }
    }

    public void drawStamina(double amount) {
        if (getStamin() > 0) {
            setStamin(getStamin() - amount);
        }
    }
    // GETTERS E SETTERS
    public Vector2F getPos() {
        return pos;
    }

    public void setSpawn(Vector2F pos) {
        this.pos.setWorldVariables(pos.getWorldLocation().xpos, pos.getWorldLocation().ypos);
    }

    public boolean isDebugging() {
        return debug;
    }

    public double getLifePoints() {

        return lifePoints + (playerLevel + 1) * 13 + 5;
    }

    public boolean isTired() {
        return this.tired;
    }

    public PlayerActions getPlayerActions() {
        return playerAct;
    }

    public PlayerAnimations getPlayerAnimations() {
        return playerAni;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int plus) {
        playerLevel = getPlayerLevel() + 1;
    }

    void setTired(boolean b) {
        this.tired = b;
    }

    public double getStamin() {
        return this.stamina;
    }

    public void setStamin(double d) {
        this.stamina = d;
    }

}
