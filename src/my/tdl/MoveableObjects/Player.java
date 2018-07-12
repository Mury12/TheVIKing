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
    public Check chk = new Check();
    private final BufferedImage msg_bgbody = loadImageFrom.LoadImageFrom(Main.class, "msg_bgbody.png");
    private final BufferedImage msg_bgtail = loadImageFrom.LoadImageFrom(Main.class, "msg_tailbg.png");
    private final BufferedImage msg_bgright = loadImageFrom.LoadImageFrom(Main.class, "msg_bgright.png");

    public double stamina = 100;
    private int playerLevel = 1;
    private double lifePoints = 105;

    private boolean tired;




    /*
     Rendering
     */
    private int renderDistanceW = 29;
    private int renderDistanceH = 14;
    public static Rectangle render;
    //TODO


    /* 0 = up
     * 1 = down
     * 2 = right
     * 3 = left
     * 4 = idel
     */
    //arrays contendo os sprites de animaçao
    private HUDmanager hudm;
    private GUImanager guim;
    private PlayerActions playerActions;
    
    public Player() {
        pos = new Vector2F(Main.width / 2 - width / 2, Main.height / 2 - height / 2); //define o player exatamente no meio da tela
    }

    public void init(World world) {
        hudm = new HUDmanager(world);
        guim = new GUImanager();
        this.world = world;
        //inicia a renderização 
        render = new Rectangle(
                (int) (pos.xpos - pos.getWorldLocation().xpos + pos.xpos - renderDistanceW / 2 + width / 2),
                (int) (pos.ypos - pos.getWorldLocation().ypos + pos.ypos - renderDistanceH / 2 + height / 2),
                renderDistanceW * 48,
                renderDistanceH * 48);

        playerAni.animatePlayer();
        playerAct.isSpawned();

    }

    //O MÉTODO TICK É RESPONSÁVEL POR ATUALIZAR AS INFORMAÇÕES PROCESSADAS NO GAME
    public void tick(double deltaTime) {

        playerMM.tick();

        //realiza a renderização em tempo real apenas dos bloccks no campo
        render = new Rectangle(
                (int) (pos.xpos - pos.getWorldLocation().xpos - renderDistanceW / 2 + width / 2),
                (int) (pos.ypos - pos.getWorldLocation().ypos - renderDistanceH / 2 + height / 2),
                renderDistanceW * 48,
                renderDistanceH * 48);

        float moveAmountu = (float) (speedUp * fixDt);
        float moveAmountd = (float) (speedDown * fixDt);
        float moveAmountl = (float) (speedLeft * fixDt);
        float moveAmountr = (float) (speedRight * fixDt);

        //CHECA COLISÃO DO MAPA COM O PLAYER
        if (up) {
            moveMapUp(moveAmountu);
            animationState = 0;
        } else {
            moveMapUpGlide(moveAmountu);
        }
        if (down) {
            moveMapDown(moveAmountd);
            animationState = 1;
        } else {
            moveMapDownGlide(moveAmountd);
        }
        if (left) {
            moveMapLeft(moveAmountl);
            animationState = 3;
        } else {
            moveMapLeftGlide(moveAmountl);
        }
        if (right) {
            moveMapRight(moveAmountr);
            animationState = 2;
        } else {
            moveMapRightGlide(moveAmountr);
        }
        if (!up && !down && !right && !left || (((left && right) || (up && down)) && (!up || !down))) {
            /*
             *Standing Still
             */
            if (isMoving()) {
                moving = false;
            }
            animationState = 4;
        }

        if (running && stamina > 0 && !tired) {
            if (getStamina() <= 1) {
                setPlayerLevel(1);
                tired = true;
                setMsg(true, randomStaminAlert());
                System.out.println("You are tired, cant run now!");
            }
            if (animationSpeed != 500) {
                animationSpeed = 500;
                ani_left.setSpeed(animationSpeed);
                ani_right.setSpeed(animationSpeed);
                ani_down.setSpeed(animationSpeed);
                ani_up.setSpeed(animationSpeed);
                maxSpeed += 64;
            }
            if (moving && !tired) {
                if (duck && running) {
                    maxSpeed = 4 * 32 + 64;
                }
                drawStamina(0.5);
            } else {
                recoverStamina(0.05);
                maxSpeed = 4 * 32;
            }
        } else {
            if (duck && tired) {
                maxSpeed -= maxSpeed - 10;
                recoverStamina(0.1);
            } else {
                if (animationSpeed != 1000) {
                    animationSpeed = 1000;
                    ani_left.setSpeed(animationSpeed);
                    ani_right.setSpeed(animationSpeed);
                    ani_down.setSpeed(animationSpeed);
                    ani_up.setSpeed(animationSpeed);
                }
                if (!duck && !running) {
                    maxSpeed = 4 * 32F;
                }
                recoverStamina(0.05);
            }
        }
    }

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
        drawAnimation(g);
        //Representação da área de renderização efetiva. //drawRect(posicao do eixo X na tela, posicao eixo Y na tela, largura, altura)
        //g.drawRect((int)pos.xpos - renderDistanceW*32/2 + width / 2, (int)pos.ypos - renderDistanceH*32 / 2 + height / 2, renderDistanceW * 32, renderDistanceH * 32);
        hudm.render(g);
        drawLifeBar(g);
        drawStaminBar(g);
        g.setColor(Color.WHITE);
        g.drawString("Level: " + getPlayerLevel(), 11, Main.height - 40);
        guim.render(g);
        playerMM.render(g);
    }

    /**
     * ***********************************************************
     */
    /**
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //pega o código da tecla pressionada

        if (key == KeyEvent.VK_UP) {
            if (!isMoving()) {
                moving = true;
            }
            up = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            if (!isMoving()) {
                moving = true;
            }
            down = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            if (!isMoving()) {
                moving = true;
            }
            left = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            if (!isMoving()) {
                moving = true;
            }
            right = true;
        }
        if (key == KeyEvent.VK_SHIFT) {
            running = true;
        }
        if (key == KeyEvent.VK_CONTROL) {
            duck = true;
        }

        if (isChatting()) {
            if (isTyping()) {
                if (key == 8) {
                    ChatBox.backspace = true;
                }
                if (key >= 32 && key <= 122) {
                    if (key != KeyEvent.VK_UP && key != KeyEvent.VK_DOWN && key != KeyEvent.VK_LEFT && key != KeyEvent.VK_RIGHT) {
                        ChatBox.typed = true;
                    }
                    setCharTyped(e.getKeyChar());
                    ChatBox.space = (key == 32);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            up = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (key == KeyEvent.VK_SHIFT) {
            running = false;
        }
        if (key == KeyEvent.VK_CONTROL) {
            duck = false;
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
            if (!isChatting()) {
                chatBox = true;
                System.out.println("Chat opened.");
            } else {
                if (isTyping()) {
                    message_sent = true;
                    ChatBox._message = ChatBox.message;
                    if (!ChatBox.chatLog.contains(ChatBox.message)) {
                        if (ChatBox.chatLog.size() * 13.5 >= Main.height / 3 - 33) {
                            ChatBox.chatLog.remove(0);
                        }
                        ChatBox.chatLog.add(ChatBox.message);
                    }
                    System.out.println("Message sent.");
                } else {
                    if (isChatting() && !isTyping()) {
                        typing = true;
                        System.out.println("Ready to type");
                    }
                }
            }
        }
        if (key == KeyEvent.VK_F2) {
            if (isChatting()) {
                resetChat();
                System.out.println("Chat closed.");
            }
        }
    }

    // GETTERS E SETTERS
    public Vector2F getPos() {
        return pos;
    }


    
    public void setSpawn(Vector2F pos) {
        this.pos.setWorldVariables(pos.getWorldLocation().xpos, pos.getWorldLocation().ypos);
    }

    public static boolean isDebugging() {
        return debug;
    }


    public double getStamina() {
        return this.stamina;
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
