/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.MoveableObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import my.project.gop.main.Vector2F;
import my.project.gop.main.loadImageFrom;
import my.tdl.gamestates.DungeonLevelLoader;
import my.tdl.generator.ChatBox;
import my.tdl.generator.MessageBaloon;
import my.tdl.main.Animator;
import my.tdl.main.Assets;
import my.tdl.main.Check;
import my.tdl.main.Main;
import my.tdl.managers.GUImanager;
import my.tdl.managers.HUDmanager;
import my.tdl.managers.Mousemanager;
import my.tdl.generator.World;

/**
 *
 * @author Andre
 */
public class Player implements KeyListener {

    private World world;
    Vector2F pos;
    private final int scale = 2;
    private final int width = 22;
    private final int height = 22;
    public static boolean up, down, left, right, running, duck;
    private static boolean debug = false;
    private float maxSpeed = 4 * 32F;
    private final float fixDt = 1F / 60F; //pixels por frames que ele andará
    //velocidade do jogador para os lados.
    private float speedUp = 0;
    private float speedDown = 0;
    private float speedLeft = 0;
    private float speedRight = 0;
    private final float slowdown = 4.93F; //quanto de incremento para o ease-in-out
    private static char keyTyped;

    private final BufferedImage msg_bgbody = loadImageFrom.LoadImageFrom(Main.class, "msg_bgbody.png");
    private final BufferedImage msg_bgtail = loadImageFrom.LoadImageFrom(Main.class, "msg_tailbg.png");
    private final BufferedImage msg_bgright = loadImageFrom.LoadImageFrom(Main.class, "msg_bgright.png");

    public static boolean moving;
    private static boolean spawned;
    public double stamina = 100;
    private int playerLevel = 1;
    private double lifePoints = 105;

    private boolean tired;

    private static boolean chatBox = false;
    public static boolean message_sent = false;
    private static boolean typing = false;
    private boolean isMsgSet = false;
    private String msg = "";

    Mousemanager playerMM = new Mousemanager();

    /*
     Rendering
     */
    private int renderDistanceW = 29;
    private int renderDistanceH = 14;
    public static Rectangle render;
    //TODO
    private int animationState = 0;
    private int animationSpeed = 1000;

    /* 0 = up
     * 1 = down
     * 2 = right
     * 3 = left
     * 4 = idel
     */
    //arrays contendo os sprites de animaçao
    private ArrayList<BufferedImage> listUp;
    Animator ani_up;
    private ArrayList<BufferedImage> listDown;
    Animator ani_down;
    private ArrayList<BufferedImage> listLeft;
    Animator ani_left;
    private ArrayList<BufferedImage> listRight;
    Animator ani_right;
    private ArrayList<BufferedImage> listIdle;
    Animator ani_idle;

    private HUDmanager hudm;
    private GUImanager guim;
    private PlayerActions playerActions;

    public Player() {
        pos = new Vector2F(Main.width / 2 - width / 2, Main.height / 2 - height / 2); //define o player exatamente no meio da tela
    }

    public void init(World world) {
        playerActions = new PlayerActions(world);
        hudm = new HUDmanager(world);
        guim = new GUImanager();
        this.world = world;
        //inicia a renderização 
        render = new Rectangle(
                (int) (pos.xpos - pos.getWorldLocation().xpos + pos.xpos - renderDistanceW / 2 + width / 2),
                (int) (pos.ypos - pos.getWorldLocation().ypos + pos.ypos - renderDistanceH / 2 + height / 2),
                renderDistanceW * 48,
                renderDistanceH * 48);

        animatePlayer();
        //UP
        ani_up = new Animator(listUp);
        ani_up.setSpeed(animationSpeed); //velocidade da animação em ms
        ani_up.play();

        //DOWN
        ani_down = new Animator(listDown);
        ani_down.setSpeed(animationSpeed);
        ani_down.play();

        //RIGHT
        ani_right = new Animator(listRight);
        ani_right.setSpeed(animationSpeed);
        ani_right.play();

        //LEFT
        ani_left = new Animator(listLeft);
        ani_left.setSpeed(animationSpeed);
        ani_left.play();

        //IDLE
        ani_idle = new Animator(listIdle);
        ani_idle.setSpeed(animationSpeed);
        ani_idle.play();

        spawned = true;
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
    public void drawStamina(double amount) {
        if (stamina > 0) {
            stamina -= amount;
        }
    }

    public void recoverStamina(double amount) {
        if (stamina < 100) {
            stamina += amount;
        }
        if (stamina >= 50) {
            tired = false;
            setMsg(false, " ");
            if (!isMsgSet()) {
                System.out.println("Ready to Run!");
            }
        }
    }
    //

    public void moveMapUp(float moveAmountu) {
        if (!Check.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu))
        )) {
            if (speedUp < maxSpeed) {
                speedUp += slowdown; //incrementa a velocidade pouco a pouco, para tornar um movimento de ease in-out suave
            } else {
                speedUp = maxSpeed;
            }
            world.map_pos.ypos -= moveAmountu;
        } else {
            speedUp = 0;
        }
    }

    public void moveMapUpGlide(float moveAmountu) {
        if (!Check.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu))
        )) {
            if (speedUp != 0) {
                speedUp -= slowdown;

                if (speedUp < 0) {
                    speedUp = 0;
                }
            }
            world.map_pos.ypos -= moveAmountu;
        } else {
            speedUp = 0;
        }

    }

    public void moveMapDown(float moveAmountd) {
        if (!Check.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
        )) {
            if (speedDown < maxSpeed) {
                speedDown += slowdown;
            } else {
                speedDown = maxSpeed;
            }
            world.map_pos.ypos += moveAmountd;
        } else {
            speedDown = 0;
        }
    }

    public void moveMapDownGlide(float moveAmountd) {
        {
            if (!Check.CollisionPlayerBlock(
                    new Point((int) (pos.xpos + world.map_pos.xpos),
                            (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                    new Point((int) (pos.xpos + world.map_pos.xpos + width),
                            (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
            )) {
                if (speedDown != 0) {
                    speedDown -= slowdown;

                    if (speedDown < 0) {
                        speedDown = 0;
                    }
                }
                world.map_pos.ypos += moveAmountd;
            } else {
                speedDown = 0;
            }
        }
    }

    public void moveMapLeft(float moveAmountl) {
        if (!Check.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos))
        )) {
            if (speedLeft < maxSpeed) {
                speedLeft += slowdown;
            } else {
                speedLeft = maxSpeed;
            }
            world.map_pos.xpos -= moveAmountl;
        } else {
            speedLeft = 0;
        }
    }

    public void moveMapLeftGlide(float moveAmountl) {
        if (!Check.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (speedLeft != 0) {
                speedLeft -= slowdown;

                if (speedLeft < 0) {
                    speedLeft = 0;
                }
            }
            world.map_pos.xpos -= moveAmountl;
        } else {
            speedLeft = 0;
        }
    }

    public void moveMapRight(float moveAmountr) {
        if (!Check.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (speedRight < maxSpeed) {
                speedRight += slowdown;
            } else {
                speedRight = maxSpeed;
            }
            world.map_pos.xpos += moveAmountr;
        } else {
            speedRight = 0;
        }
    }

    public void moveMapRightGlide(float moveAmountr) {
        if (!Check.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (speedRight != 0) {
                speedRight -= slowdown;

                if (speedRight < 0) {
                    speedRight = 0;
                }
            }
            world.map_pos.xpos += moveAmountr;
        } else {
            speedRight = 0;
        }
    }

    public void drawLifeBar(Graphics2D g) {
        g.setColor(new Color((int) (getLifePoints() * (255 / getLifePoints())), (int) (getLifePoints() * (68 / getLifePoints())), (int) (getLifePoints() * (68 / getLifePoints()))));
        g.fillRect(10, Main.height - 35, (int) (getLifePoints() * (100 / getLifePoints())), 30);
        if (getLifePoints() > ((playerLevel + 1) * 100 / playerLevel + 5) / 2) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString("" + (int) getLifePoints(), 129 / 2 - 3 * 4, Main.height - 17);
        g.setColor(Color.WHITE);
        g.drawRect(10, Main.height - 35, 100, 30);
    }

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

    public void drawStaminBar(Graphics2D g) {

        g.setColor(new Color(255, (int) (getStamina() * 2.5), 0));
        g.fillRect(115, Main.height - 35, (int) getStamina(), 30);
        if (getStamina() > 50) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString("" + (int) getStamina(), 168 - 3 * 4, Main.height - 17);

        if (isTired()) {
            if (getStamina() < 20) {
                MessageBaloon.getFinalMessage(msg, g);
            }
        }
        g.setColor(Color.WHITE);
        g.drawRect(115, Main.height - 35, 100, 30);
    }

    public void drawAnimation(Graphics2D g) {
        if (animationState == 0) {
            g.drawImage(ani_up.sprite, (int) pos.xpos - width / 2, (int) pos.ypos - height, width * scale, height * scale, null);
            if (up) {
                ani_up.update(System.currentTimeMillis()); //atualiza o sprite constantemente para animar
            }
        }
        //DOWN
        if (animationState == 1) {
            g.drawImage(ani_down.sprite, (int) pos.xpos - width / 2, (int) pos.ypos - height, width * scale, height * scale, null);
            if (down) {
                ani_down.update(System.currentTimeMillis()); //atualiza o sprite constantemente para animar
            }
        }
        //RIGHT
        if (animationState == 2) {
            g.drawImage(ani_right.sprite, (int) pos.xpos - width / 2, (int) pos.ypos - height, width * scale, height * scale, null);
            if (right) {
                ani_right.update(System.currentTimeMillis()); //atualiza o sprite constantemente para animar
            }
        }
        //LEFT
        if (animationState == 3) {
            g.drawImage(ani_left.sprite, (int) pos.xpos - width / 2, (int) pos.ypos - height, width * scale, height * scale, null);
            if (left) {
                ani_left.update(System.currentTimeMillis()); //atualiza o sprite constantemente para animar
            }
        }
        //IDLE
        if (animationState == 4) {
            g.drawImage(ani_idle.sprite, (int) pos.xpos - width / 2, (int) pos.ypos - height, width * scale, height * scale, null);
            if (!right && !left && !down && !up) {
                ani_idle.update(System.currentTimeMillis());
            }
        }
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

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getSlowdown() {
        return slowdown;
    }

    private void animatePlayer() {
        listUp = new ArrayList<>();
        listDown = new ArrayList<>();
        listLeft = new ArrayList<>();
        listRight = new ArrayList<>();
        listIdle = new ArrayList<>();

        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(0, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));
        listUp.add(Assets.player.getTile(16, 32, 16, 16));

        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(0, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));
        listDown.add(Assets.player.getTile(16, 48, 16, 16));

        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(32, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(48, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));
        listLeft.add(Assets.player.getTile(64, 48, 16, 16));

        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(32, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(48, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));
        listRight.add(Assets.player.getTile(64, 32, 16, 16));

        listIdle.add(Assets.player.getTile(0, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16 * 2, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16 * 2, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16 * 2, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16, 16, 16, 16));
        listIdle.add(Assets.player.getTile(16, 16, 16, 16));
        for (int i = 0; i < 100; i++) {
            listIdle.add(Assets.player.getTile(0, 16, 16, 16));
        }
    }

    public void setSpawn(Vector2F pos) {
        this.pos.setWorldVariables(pos.getWorldLocation().xpos, pos.getWorldLocation().ypos);
    }

    public static boolean isDebugging() {
        return debug;
    }

    public boolean isMoving() {
        return this.moving;
    }

    public boolean hasSpawned() {
        return this.spawned;
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

    public void setMsg(boolean set, String msg) {
        this.isMsgSet = set;
        this.msg = msg;
    }

    public boolean isMsgSet() {
        return this.isMsgSet;
    }

    public PlayerActions getPlayerActions() {
        return playerActions;
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

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int plus) {
        playerLevel = getPlayerLevel() + 1;
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

    public class PlayerActions {

        private World world;

        public PlayerActions(World world) {
            this.world = world;
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
    }

}
