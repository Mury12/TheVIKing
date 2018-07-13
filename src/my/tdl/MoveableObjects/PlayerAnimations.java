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

    Vector2F pos;
    public static final int scale = 2;
    public static final int width = 22;
    public static final int height = 22;
    private Player p;
    public static boolean up, down, left, right, running, duck;

    private final int renderDistanceW = 29;
    private final int renderDistanceH = 14;
    public Rectangle render;

    private int animationState = 4;
    private int animationSpeed = 1000;

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

    float moveAmountu;
    float moveAmountd;
    float moveAmountl;
    float moveAmountr;

    private World world;

    public PlayerAnimations(Player p) {
        this.p = p;
    }

    public PlayerAnimations(Vector2F pos, World world, Player p) {
        this.pos = pos;
        this.p = p;
        this.world = world;
    }

    public PlayerAnimations(Vector2F pos, Player p) {
        this.pos = pos;
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

    public void drawStamina(double amount
    ) {
        if (p.getStamin() > 0) {
            p.setStamin(p.getStamin() - amount);

        }
    }
//vai pro hud

    public void drawStaminBar(Graphics2D g
    ) {

        g.setColor(new Color(255, (int) (p.getStamin() * 2.5), 0));
        g.fillRect(115, Main.height - 35, (int) p.getStamin(), 30);

        if (p.getStamin() > 50) {
            g.setColor(Color.BLACK);

        } else {
            g.setColor(Color.WHITE);

        }
        g
                .drawString("" + (int) p.getStamin(), 168 - 3 * 4, Main.height - 17);

        if (p.isTired()) {
            if (p.getStamin() < 20) {
                MessageBaloon.getFinalMessage(p.getPlayerActions().getMsg(), g);

            }
        }
        g.setColor(Color.WHITE);
        g.drawRect(115, Main.height - 35, 100, 30);

    }

    public void recoverStamina(double amount
    ) {
        if (p.getStamin() < 100) {
            p.setStamin(p.getStamin() + amount);

        }
        if (p.getStamin() >= 50) {
            p.setTired(false);
            p.getPlayerActions().setMsg(false, " ");

        }
    }
    //

    public void moveMapUp(float moveAmountu) {
        System.out.println("moveMapUp");

        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu))
        )) {
            if (p.getPlayerActions().getSpeed("up") < p.getPlayerActions().getMaxSpeed()) {
                p.getPlayerActions().setSpeed("up", p.getPlayerActions().getSpeed("up") + p.getPlayerActions().getSlowdown()); //incrementa a velocidade pouco a pouco, para tornar um movimento de ease in-out suave

            } else {
                p.getPlayerActions().setSpeed("", p.getPlayerActions().getMaxSpeed());

            }
            world.map_pos.ypos -= moveAmountu;

        } else {
            p.getPlayerActions().setSpeed("up", 0);

        }
    }

    public void moveMapUpGlide(float moveAmountu) {
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
            p.getPlayerActions().setSpeed("up", 0);

        }

    }

    public void moveMapDown(float moveAmountd) {
        System.out.println("moveMapDown");

        if (!p.chk
                .CollisionPlayerBlock(
                        new Point((int) (pos.xpos + world.map_pos.xpos),
                                (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                        new Point((int) (pos.xpos + world.map_pos.xpos + width),
                                (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
                )) {
            if (p.getPlayerActions().getSpeed("down") < p.getPlayerActions().getMaxSpeed()) {
                p.getPlayerActions().setSpeed("down", p.getPlayerActions().getSpeed("up") + p.getPlayerActions().getSlowdown());

            } else {
                p.getPlayerActions().setSpeed("down", p.getPlayerActions().getMaxSpeed());

            }
            world.map_pos.ypos += moveAmountd;

        } else {
            p.getPlayerActions().setSpeed("down", 0);

        }
    }

    public void moveMapDownGlide(float moveAmountd
    ) {

        if (!p.chk
                .CollisionPlayerBlock(
                        new Point((int) (pos.xpos + world.map_pos.xpos),
                                (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                        new Point((int) (pos.xpos + world.map_pos.xpos + width),
                                (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
                )) {
            if (p.getPlayerActions().getSpeed("down") != 0) {
                p.getPlayerActions().setSpeed("down", p.getPlayerActions().getSpeed("up") - p.getPlayerActions().getSlowdown());

                if (p.getPlayerActions().getSpeed("down") < 0) {
                    p.getPlayerActions().setSpeed("down", 0);

                }
            }
            world.map_pos.ypos += moveAmountd;

        } else {
            p.getPlayerActions().setSpeed("down", 0);

        }

    }

    public void moveMapLeft(float moveAmountl) {
        System.out.println("moveMapLeft");

        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos))
        )) {
            if (p.getPlayerActions().getSpeed("left") < p.getPlayerActions().getMaxSpeed()) {
                p.getPlayerActions().setSpeed("left", p.getPlayerActions().getSpeed("left") + p.getPlayerActions().getSlowdown()
                );

            } else {
                p.getPlayerActions().setSpeed("left", p.getPlayerActions().getMaxSpeed());

            }
            world.map_pos.xpos -= moveAmountl;

        } else {
            p.getPlayerActions().setSpeed("left", 0);

        }
    }

    public void moveMapLeftGlide(float moveAmountl) {
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

    public void moveMapRight(float moveAmountr) {
        System.out.println("moveMapRight");

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

        } else {
            p.getPlayerActions().setSpeed("right", 0);

        }
    }

    public void moveMapRightGlide(float moveAmountr
    ) {
        if (!p.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (p.getPlayerActions().getSpeed("right") != 0) {
                p.getPlayerActions().setSpeed("right", p.getPlayerActions().getSpeed("right") + p.getPlayerActions().getSlowdown());

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
//goes to gui

    int getWidth() {
        return PlayerAnimations.width;
    }

    int getHeight() {
        return PlayerAnimations.height;
    }

    public void setPlayerFigure() {
        System.out.println(up);
        if (up) {
            System.out.println("setPlayerFigure");
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
            if (p.getPlayerActions().isMoving()) {
                p.getPlayerActions().moving = false;

            }
            animationState = 4;

        }

        if (running && p.getStamin() > 0 && !p.isTired()) {
            if (p.getStamin() <= 1) {
                p.setPlayerLevel(1);
                p.setTired(true);
                p.getPlayerActions().setMsg(true, p.randomStaminAlert());
                System.out.println("You are tired, cant run now!");

            }
            if (animationSpeed != 500) {
                animationSpeed = 500;
                ani_left.setSpeed(animationSpeed);
                ani_right.setSpeed(animationSpeed);
                ani_down.setSpeed(animationSpeed);
                ani_up.setSpeed(animationSpeed);
                p.getPlayerActions().setMaxSpeed(p.getPlayerActions().getMaxSpeed() + 64);

            }
            if (p.getPlayerActions().isMoving() && !p.isTired()) {
                if (duck && running) {
                    p.getPlayerActions().setMaxSpeed(6 * 32);

                }
                drawStamina(0.5);

            } else {
                recoverStamina(0.05);
                p.getPlayerActions().setMaxSpeed(4 * 32);

            }
        } else {
            if (duck && p.isTired()) {
                p.getPlayerActions().setMaxSpeed(p.getPlayerActions().getMaxSpeed() - 10);
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
                    p.getPlayerActions().setMaxSpeed(4 * 32F);
                }
                recoverStamina(0.05);
            }
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
