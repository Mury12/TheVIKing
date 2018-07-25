/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.managers;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import my.project.gop.main.Light;
import my.project.gop.main.Vector2F;
import my.project.gop.main.loadImageFrom;
import my.tdl.MoveableObjects.Player;
import static my.tdl.MoveableObjects.PlayerAnimations.height;
import static my.tdl.MoveableObjects.PlayerAnimations.width;
import my.tdl.generator.World;
import my.tdl.main.Main;

/**
 *
 * @author Andre
 */
public class HUDmanager {

    private Player player;
    private BufferedImage light;
    private BufferedImage lightmap = new BufferedImage(100 * 32, 100 * 32, BufferedImage.TYPE_INT_ARGB);
    public ArrayList<Light> lights = new ArrayList<>();
    private Vector2F lightm = new Vector2F();
    private World world;

    private int[] line = {1, 1};

    public HUDmanager(Player player) {
        this.player = player;
        //addLights();
        light = loadImageFrom.LoadImageFrom(Main.class, "light.png");
    }

    public HUDmanager(World world) {
        this.world = world;
    }

    public HUDmanager(Player player, World world) {
        this.world = world;
        this.player = player;
    }

    private static Polygon up;
    private static Polygon down;
    private static Polygon right;
    private static Polygon left;

    private void addLights() {
//        lights.add(new Light(200, 200, 200, 120));
    }

    public void updateLights() {
        Graphics2D g = null;

        if (g == null) {
            g = (Graphics2D) lightmap.getGraphics();
        }
        g.setColor(new Color(0, 0, 0, 255));
        g.fillRect(0, 0, lightmap.getWidth(), lightmap.getHeight());
        g.setComposite(AlphaComposite.DstOut);

        for (Light light : lights) {
            light.render(g);
        }
        g.dispose();
    }

    public void render(Graphics2D g) {
        /*lights
         *updateLights();
         *g.drawImage(lightmap, (int)lightm.getWorldLocation().xpos, (int)lightm.getWorldLocation().ypos, null);
         */
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.width, Main.height / 6);
        g.fillRect(0, (Main.height * 5) / 6, Main.width, Main.height / 6);
        g.setColor(Color.WHITE);

        //
        Player.getPlayerAnimations().drawLifeBar(g);
        Player.getPlayerAnimations().drawStaminBar(g);

        //g.drawImage(light, 0, 0, Main.width, Main.height, null);
        g.setFont(new Font("Arial", 0, 10));
        if (world.getPlayer().isDebugging()) {
            debug(g);
        }
    }

    public static Polygon getDownPol() {
        return down;
    }

    public static Polygon getLeftPol() {
        return left;
    }

    public static Polygon getRightPol() {
        return right;
    }

    public static Polygon getUpPol() {
        return up;
    }

    private void debug(Graphics2D g) {
        String dbg_string = "[DEBUGGING]";
        //Column 1
        g.drawString(dbg_string + "", 10, 10);
        g.drawString("MapXpos:      " + (int) world.getWorldPos().xpos, 10, 23);
        g.drawString("MapYpos:      " + (int) world.getWorldPos().ypos, 10, 36);
        g.drawString("WBlocks:      " + world.getTiles().getBlocks().size(), 10, 49);
        g.drawString("CWBlocks:     " + world.getTiles().getLoaded_blocks().size(), 10, 49 + 13);
        g.drawString("CurrBlock:    " + world.getCurrentBlock().getBlockTypeName(), 10, 49 + 13 * 2);
        g.drawString("BlockSolid:   " + world.getCurrentBlock().isSolid(), 10, 49 + 13 * 3);
        g.drawString("MAXSpeed:     " + world.getPlayer().getPlayerActions().getMaxSpeed(), 10, 49 + 13 * 4);
        g.drawString("Speed:        " + (int) world.getPlayer().getPlayerActions().getSpeed(), 10, 49 + 13 * 5);
        g.drawString("Stamin:       " + (int) world.getPlayer().getStamin(), 10, 49 + 13 * 6);
        //Column 2
        g.drawString("moveAmount U: " + world.getPlayer().getPlayerAnimations().moveAmountu, 150, 10);
        g.drawString("moveAmount D: " + world.getPlayer().getPlayerAnimations().moveAmountd, 150, 23);
        g.drawString("moveAmount L: " + world.getPlayer().getPlayerAnimations().moveAmountl, 150, 36);
        g.drawString("moveAmount R: " + world.getPlayer().getPlayerAnimations().moveAmountr, 150, 49);
        g.drawString("isMoving:     " + world.getPlayer().getPlayerActions().isMoving(), 150, 49 + 13);
        g.drawString("isRunning:    " + world.getPlayer().getPlayerActions().isRunning(), 150, 49 + 13 * 2);
        g.drawString("isTired:      " + world.getPlayer().isTired(), 150, 49 + 13 * 3);
        g.drawString("isMsgSet:     " + world.getPlayer().getPlayerActions().isMsgSet(), 150, 49 + 13 * 4);
        g.drawString("keyPressed:   " + (int) world.getPlayer().getKp(), 150, 49 + 13 * 6);
        //Column 3
        g.drawString("Surrounding Blocks:", 320, 10);
        g.drawString("UBlock:   " + world.getNextBlock("up", 0).blocktype, 320, 23);
        g.drawString("DBlock:   " + world.getNextBlock("down", 0).blocktype, 320, 36);
        g.drawString("LBlock:   " + world.getNextBlock("left", 0).blocktype, 320, 49);
        g.drawString("RBlock:   " + world.getNextBlock("right", 0).blocktype, 320, 49 + 13);
        //Column 4
        g.drawString("Colliding:", 470, 10);
        g.drawString("" + world.getPlayer().chk.hasColided(world, "up"), 470, 23);
        g.drawString("" + world.getPlayer().chk.hasColided(world, "down"), 470, 36);
        g.drawString("" + world.getPlayer().chk.hasColided(world, "left"), 470, 49);
        g.drawString("" + world.getPlayer().chk.hasColided(world, "right"), 470, 49+13);


        drawDebugPolygon(g);
    }

    private void drawDebugPolygon(Graphics2D g) {
        //Polígonos a serem desenhados na tela; ux = int[]={top-r, bot-r, bot-l, top-l] <-- de acordo com a largura
        int[] ux = new int[]{Main.width, Main.width / 2, Main.width / 2, 0};
        //uy int[]={top-r, bot-r, bot-l, top-l] de acordo com a altura
        int[] uy = new int[]{Main.height / 6, Main.height / 2, Main.height / 2, Main.height / 6};
        up = new Polygon(ux, uy, ux.length);
        g.drawPolygon(up);

        int[] dx = new int[]{Main.width, Main.width / 2, Main.width / 2, 0};
        int[] dy = new int[]{Main.height - Main.height / 6, Main.height / 2, Main.height / 2, Main.height - Main.height / 6};
        down = new Polygon(dx, dy, dx.length);
        g.drawPolygon(down);
//
//        int[] rx = new int[]{Main.width, Main.width / 2, Main.width / 2, Main.width};
//        int[] ry = new int[]{Main.height, Main.height / 2, Main.height / 2, 0};
//        right = new Polygon(rx, ry, rx.length);
//        g.drawPolygon(right);
//
//        int[] lx = new int[]{0, Main.width / 2, Main.width / 2, 0};
//        int[] ly = new int[]{Main.height, Main.height / 2, Main.height / 2, 0};
//        left = new Polygon(lx, ly, lx.length);
//        g.drawPolygon(left);  
    }
}
