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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import my.project.gop.main.Vector2F;
import my.tdl.generator.MessageBaloon;
import my.tdl.generator.World;
import my.tdl.main.Animator;
import my.tdl.main.Assets;
import my.tdl.main.Main;

/**
 *
 * @author andremury
 */
public class PlayerAnimations {

    public static Vector2F pos;
    public static final int scale = 2;
    public static final int width = 22;
    public static final int height = 22;
    private Player p;
    public static boolean up, down, left, right;

    private final int renderDistanceW = 29;
    private final int renderDistanceH = 14;
    public Rectangle render;

    private int animationState = 4;
    private int animationSpeed = 500;

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

    public float moveAmountu;
    public float moveAmountd;
    public float moveAmountl;
    public float moveAmountr;

    private World world;

    public PlayerAnimations(Player p) {
        this.p = p;
    }

    public PlayerAnimations(Vector2F position, World world, Player p) {
        pos = position;
        this.p = p;
        this.world = world;
    }

    public PlayerAnimations(Vector2F position, Player p) {
        pos = position;
        this.p = p;
    }

    public int getRenderDistanceH() {
        return renderDistanceH;

    }

    public int getRenderDistanceW() {
        return renderDistanceW;

    }

    public Rectangle
            getRender() {
        return render;

    }

    public void setRender(Rectangle render) {
        this.render = render;

    }

    public void setWorld(World world) {
        this.world = world;
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

    public void animatePlayer() {

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
        ani_left
                .setSpeed(animationSpeed);
        ani_left.play();

        //IDLE
        ani_idle = new Animator(listIdle);
        ani_idle.setSpeed(animationSpeed);
        ani_idle.play();

    }

//vai pro hud
    //
    public void moveMapUp() {

        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.getWorldPos().xpos),
                        (int) (pos.ypos + world.getWorldPos().ypos - moveAmountu)),
                new Point((int) (pos.xpos + world.getWorldPos().xpos + width),
                        (int) (pos.ypos + world.getWorldPos().ypos - moveAmountu))
        )) {
            if (p.getPlayerActions().getSpeed("up") < p.getPlayerActions().getMaxSpeed()) {
                p.getPlayerActions().setSpeed("up", p.getPlayerActions().getSpeed("up") + p.getPlayerActions().getSlowdown()); //incrementa a velocidade pouco a pouco, para tornar um movimento de ease in-out suave

            } else {
                p.getPlayerActions().setSpeed("", p.getPlayerActions().getMaxSpeed());

            }
            world.map_pos.ypos -= moveAmountu;
        } else {
            p.getPlayerActions().setSpeed("up", 0);
            System.out.println("coliding");
        }
    }

    public void moveMapUpGlide() {
        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu))
        )) {
            if (p.getPlayerActions().getSpeed("up") != 0) {
                p.getPlayerActions().setSpeed("up", p.getPlayerActions().getSpeed("up") - p.getPlayerActions().getSlowdown());

                if (p.getPlayerActions().getSpeed("up") < 0) {
                    p.getPlayerActions().setSpeed("up", 0);

                }
            }
            world.map_pos.ypos -= moveAmountu;

        } else {
            Player.getPlayerActions().setSpeed("up", 0);

        }

    }

    public void moveMapDown() {

        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
        )) {
            if (p.getPlayerActions().getSpeed("down") < p.getPlayerActions().getMaxSpeed()) {
                p.getPlayerActions().setSpeed("down", p.getPlayerActions().getSpeed("down") + p.getPlayerActions().getSlowdown());

            } else {
                p.getPlayerActions().setSpeed("down", p.getPlayerActions().getMaxSpeed());

            }
            world.map_pos.ypos += moveAmountd;

        } else {
            p.getPlayerActions().setSpeed("down", 0);

        }
    }

    public void moveMapDownGlide() {

        if (!p.chk
                .CollisionPlayerBlock(
                        new Point((int) (pos.xpos + world.map_pos.xpos),
                                (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                        new Point((int) (pos.xpos + world.map_pos.xpos + width),
                                (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
                )) {
            if (p.getPlayerActions().getSpeed("down") != 0) {
                p.getPlayerActions().setSpeed("down", p.getPlayerActions().getSpeed("down") - p.getPlayerActions().getSlowdown());

                if (p.getPlayerActions().getSpeed("down") < 0) {
                    p.getPlayerActions().setSpeed("down", 0);

                }
            }
            world.map_pos.ypos += moveAmountd;

        } else {
            p.getPlayerActions().setSpeed("down", 0);

        }

    }

    public void moveMapLeft() {

        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos))
        )) {
            if (p.getPlayerActions().getSpeed("left") < p.getPlayerActions().getMaxSpeed()) {
                p.getPlayerActions().setSpeed(
                        "left", p.getPlayerActions().getSpeed("left")
                        + p.getPlayerActions().getSlowdown()
                );

            } else {
                p.getPlayerActions().setSpeed("left", p.getPlayerActions().getMaxSpeed());
            }
            world.map_pos.xpos -= moveAmountl;

        } else {
            p.getPlayerActions().setSpeed("left", 0);

        }
    }

    public void moveMapLeftGlide() {
        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (p.getPlayerActions().getSpeed("left") != 0) {
                p.getPlayerActions().setSpeed("left", p.getPlayerActions().getSpeed("left") - p.getPlayerActions().getSlowdown());

                if (p.getPlayerActions().getSpeed("left") < 0) {
                    p.getPlayerActions().setSpeed("left", 0);

                }
            }
            world.map_pos.xpos -= moveAmountl;

        } else {
            p.getPlayerActions().setSpeed("left", 0);

        }
    }

    public void moveMapRight() {

        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (p.getPlayerActions().getSpeed("right") < p.getPlayerActions().getMaxSpeed()) {
                p.getPlayerActions().setSpeed("right", p.getPlayerActions().getSpeed("right") + p.getPlayerActions().getSlowdown());

            } else {
                p.getPlayerActions().setSpeed("right", p.getPlayerActions().getMaxSpeed());

            }
            world.map_pos.xpos += moveAmountr;
//            System.out.println(world.map_pos.xpos + " " + moveAmountr);

        } else {
            p.getPlayerActions().setSpeed("right", 0);

        }
    }

    public void moveMapRightGlide() {
        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (p.getPlayerActions().getSpeed("right") != 0) {
                p.getPlayerActions().setSpeed("right", p.getPlayerActions().getSpeed("right") - p.getPlayerActions().getSlowdown());

                if (p.getPlayerActions().getSpeed("right") < 0) {
                    p.getPlayerActions().setSpeed("right", 0);

                }
            }
            world.map_pos.xpos += moveAmountr;

        } else {
            p.getPlayerActions().setSpeed("right", 0);

        }
    }

    public void drawLifeBar(Graphics2D g) {
        g.setColor(
                new Color(
                        (int) (p.getLifePoints() * (255 / p.getLifePoints())),
                        (int) (p.getLifePoints() * (68 / p.getLifePoints())),
                        (int) (p.getLifePoints() * (68 / p.getLifePoints()))));
        g.fillRect(
                10, Main.height - 35,
                (int) (p.getLifePoints() * (100 / p.getLifePoints())),
                30);

        if (p.getLifePoints() > ((p.getPlayerLevel() + 1) * 100 / p.getPlayerLevel() + 5) / 2) {
            g.setColor(Color.BLACK);

        } else {
            g.setColor(Color.WHITE);

        }
        g.drawString("" + (int) p.getLifePoints(), 129 / 2 - 3 * 4, Main.height
                - 17);
        g.setColor(Color.WHITE);
        g.drawRect(10, Main.height - 35, 100, 30);

    }

    public void drawStaminBar(Graphics2D g) {

        g.setColor(new Color(255, (int) (p.getStamin() * 2.5), 0));
        g.fillRect(115, Main.height - 35, (int) p.getStamin(), 30);

        if (p.getStamin() > 50) {
            g.setColor(Color.BLACK);

        } else {
            g.setColor(Color.WHITE);

        }
        g.drawString("" + (int) p.getStamin(), 168 - 3 * 4, Main.height - 17);
        
        if (p.getPlayerActions().isMsgSet()) {
            MessageBaloon.getFinalMessage(p.getPlayerActions().getMsg(), g);
        }
        
        g.setColor(Color.WHITE);
        g.drawRect(115, Main.height - 35, 100, 30);

    }

    int getWidth() {
        return PlayerAnimations.width;
    }

    int getHeight() {
        return PlayerAnimations.height;
    }

    public void setPlayerFigure() {
        if (up) {
            moveMapUp();
            animationState = 0;

        } else {
            moveMapUpGlide();
        }
        if (down) {
            moveMapDown();
            animationState = 1;

        } else {
            moveMapDownGlide();

        }
        if (left) {
            moveMapLeft();
            animationState = 3;

        } else {
            moveMapLeftGlide();

        }
        if (right) {
            moveMapRight();
            animationState = 2;

        } else {
            moveMapRightGlide();

        }
        if (!up && !down && !right && !left || (((left && right) || (up && down)) && (!up || !down))) {
            /*
             *Standing Still
             */
            if (p.getPlayerActions().isMoving()) {
                p.getPlayerActions().moving = false;

            }
            animationState = 4;

        }
    }

    public void setMoveAmounts() {
        this.moveAmountu = (float) (this.p.getPlayerActions().getSpeed("up")
                * p.getPlayerActions().fixDt);
        this.moveAmountd = (float) (p.getPlayerActions().getSpeed("down")
                * p.getPlayerActions().fixDt);
        this.moveAmountl = (float) (p.getPlayerActions().getSpeed("left")
                * p.getPlayerActions().fixDt);
        this.moveAmountr = (float) (p.getPlayerActions().getSpeed("right")
                * p.getPlayerActions().fixDt);
    }
}
