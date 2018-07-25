package my.tdl.MoveableObjects;

import java.awt.Color;
import java.awt.Graphics2D;
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
import my.tdl.main.Check;
import my.tdl.main.Main;
import my.tdl.managers.*;
import my.tdl.generator.World;

/**
 *
 * @author Andre
 */
public class Player {

    private World world;

    //velocidade do jogador para os lados.
    private static PlayerActions playerAct;
    private static PlayerAnimations playerAni;
    private final BufferedImage msg_bgbody = loadImageFrom.LoadImageFrom(Main.class, "msg_bgbody.png");
    private final BufferedImage msg_bgtail = loadImageFrom.LoadImageFrom(Main.class, "msg_tailbg.png");
    private final BufferedImage msg_bgright = loadImageFrom.LoadImageFrom(Main.class, "msg_bgright.png");

    public static double stamina = 100;
    private static int playerLevel = 1;
    private static double lifePoints = 105;

    public Check chk = new Check();
    private static boolean tired;

    private KeyManager keym;
    private HUDmanager hudm;
    private GUImanager guim;
    private final Vector2F pos;
    
    public Player() {
        pos = new Vector2F(Main.width / 2 - PlayerAnimations.width / 2, Main.height / 2 - PlayerAnimations.height / 2); //define o player exatamente no meio da tela
        playerAct = new PlayerActions(pos, this);
        playerAni = new PlayerAnimations(pos, this);
    }

    public void init(World world) {
        hudm = new HUDmanager(world);
        guim = new GUImanager(this);
        keym = new KeyManager(this);
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
    /**
     * Stamina Alert This method is responsible for random select a message to
     * display when player's stamina is out. To add more sentences, just use a
     * new array line adding msg.add("Your Blowing Message") and the function
     * will automatically calculate the random number from the Array to select.
     *
     * @return a final message.
     */
    public String randomStaminAlert() {
        ArrayList<String> msg = new ArrayList<>();
        Random rand = new Random();
        msg.add("I'd be glad if you stop running now..");
        msg.add("Wow! That was really tiring!");
        msg.add("My stamin is out, I can't run.");
        msg.add("PLEASE BABY! LET ME REST A LITTLE!!");
        msg.add("F**K! I HATE THIS HUNTS!");
        msg.add("HELLS BELLS! I really wanted to catch it but.. you know.. I'm a FAG!");

        return msg.get(rand.nextInt(msg.size()));
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
        g.setColor(Color.WHITE);
        g.drawString("Level: " + getPlayerLevel(), 11, Main.height - 40);
        guim.render(g);
        playerAct.playerMM.render(g);
    }


    public void recoverStamina(double amount) {
        if (getStamin() < 100) {
            setStamin(getStamin() + amount);
        }
    }

    /**
     * Stamina Drawing This is used to draw stamina from the character total
     * stamina. For a while it is used only by the running system.
     *
     */
    public void drawStamina() {
        if (getStamin() > 1) {
            setStamin(getStamin() - ((double)((playerLevel+1)/Math.pow(playerLevel, 1.1))));
        }
    }

    // GETTERS E SETTERS
    public Vector2F getPos() {
        return pos;
    }

    /**
     * Player Spawn Location This function sets the Player spawn position
     * according to the map.
     *
     * @param pos is the Vector2F position to set the point.
     */
    public void setSpawn(Vector2F pos) {
        this.pos.setWorldVariables(pos.getWorldLocation().xpos, pos.getWorldLocation().ypos);
    }

    /**
     * Debug Screen This function is responsible to command a debug screen. When
     * its value returns true the debug function starts.
     *
     * @return a boolean param to start the debug screen.
     */


    /**
     * GET Player Life Points This function is responsible to return the total
     * amount of current player life points.
     *
     * @return a double (converted to integer on showing) value.
     */
    public double getLifePoints() {
        return lifePoints;
    }

    /**
     * Tired System This function is responsible to indicate that the player is
     * tired and can not run or perform any waste of stamina.
     *
     * @return true or false.
     */
    public boolean isTired() {
        return this.tired;
    }

    void setTired(boolean b) {
        this.tired = b;
    }

    public static PlayerActions getPlayerActions() {
        return playerAct;
    }

    public static PlayerAnimations getPlayerAnimations() {
        return playerAni;
    }

    /**
     * This function is responsible for getting the level of the player.
     *
     * @return an integer indicating the player level.
     */
    public int getPlayerLevel() {
        return playerLevel;
    }

    /**
     * This function is responsible for increase player's level.
     *
     * @param plus will be calculated based on Experience Points given to the
     * player and then converted to level.
     */
    public void setPlayerLevel(int plus) {
        playerLevel = getPlayerLevel() + 1;
        setLifePoints();
        setStamin();
    }

    public static double getStamin() {
        return stamina;
    }

    /**
     * This function is responsible for setting up the initial player's stamina.
     *
     * @param d the amount of initial stamina.
     */
    public static void setStamin(double d) {
        stamina = d;
    }

    /**
     * This function is responsible for updating player's stamina points. This
     * means that the Stamina Points will be calculated by the level, abilities
     * and equipments wielded.
     */
    public void setStamin() {
        stamina = 100;
    }

    /**
     * This function is responsible for updating player's life points. The Life
     * Points are calculates by the level, class, abilities and equipments
     * wielded.
     */
    private void setLifePoints() {
        lifePoints = 100 + (playerLevel + 1) * 13 + 5;
    }

}
