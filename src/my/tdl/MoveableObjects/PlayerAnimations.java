/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.MoveableObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
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
    private final int scale = 2;
    private final int width = 22;
    private final int height = 22;
    private PlayerActions p;
    private Player player;
    public static boolean up, down, left, right, running, duck;

    private int animationState = 0;
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

    private World world;

    public PlayerAnimations(Vector2F pos, World world) {
        this.pos = pos;
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

    public void drawStamina(double amount) {
        if (player.getStamin() > 0) {
            player.setStamin(player.getStamin() - amount);
        }
    }

    public void drawStaminBar(Graphics2D g) {

        g.setColor(new Color(255, (int) (player.getStamin() * 2.5), 0));
        g.fillRect(115, Main.height - 35, (int) player.getStamin(), 30);
        if (player.getStamin() > 50) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString("" + (int) player.getStamin(), 168 - 3 * 4, Main.height - 17);

        if (player.isTired()) {
            if (player.getStamin() < 20) {
                MessageBaloon.getFinalMessage(p.getMsg(), g);
            }
        }
        g.setColor(Color.WHITE);
        g.drawRect(115, Main.height - 35, 100, 30);
    }

    public void recoverStamina(double amount) {
        if (player.getStamin() < 100) {
            player.setStamin(player.getStamin() + amount);
        }
        if (player.getStamin() >= 50) {
            player.setTired(false);
            player.setMsg(false, " ");
        }
    }
    //

    public void moveMapUp(float moveAmountu) {
        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu))
        )) {
            if (p.getSpeed("up") < p.getMaxSpeed()) {
                p.setSpeed("up", p.getSpeed("up") + p.getSlowdown()); //incrementa a velocidade pouco a pouco, para tornar um movimento de ease in-out suave
            } else {
                p.setSpeed("", p.getMaxSpeed());
            }
            world.map_pos.ypos -= moveAmountu;
        } else {
            p.setSpeed("up", 0);
        }
    }

    public void moveMapUpGlide(float moveAmountu) {
        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos - moveAmountu))
        )) {
            if (p.getSpeed("up") != 0) {
                p.setSpeed("up", p.getSpeed("up") - p.getSlowdown());

                if (p.getSpeed("up") < 0) {
                    p.setSpeed("up", 0);
                }
            }
            world.map_pos.ypos -= moveAmountu;
        } else {
            p.setSpeed("up", 0);
        }

    }

    public void moveMapDown(float moveAmountd) {
        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
        )) {
            if (p.getSpeed("down") < p.getMaxSpeed()) {
                p.setSpeed("down", p.getSpeed("up") + p.getSlowdown());
            } else {
                p.setSpeed("down", p.getMaxSpeed());
            }
            world.map_pos.ypos += moveAmountd;
        } else {
            p.setSpeed("down", 0);
        }
    }

    public void moveMapDownGlide(float moveAmountd) {

        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width),
                        (int) (pos.ypos + world.map_pos.ypos + height + moveAmountd))
        )) {
            if (p.getSpeed("down") != 0) {
                p.setSpeed("down", p.getSpeed("up") - p.getSlowdown());

                if (p.getSpeed("down") < 0) {
                    p.setSpeed("down", 0);
                }
            }
            world.map_pos.ypos += moveAmountd;
        } else {
            p.setSpeed("down", 0);
        }

    }

    public void moveMapLeft(float moveAmountl) {
        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos))
        )) {
            if (p.getSpeed("left") < p.getMaxSpeed()) {
                p.setSpeed("left", p.getSpeed("left") + p.getSlowdown());
            } else {
                p.setSpeed("left", p.getMaxSpeed());
            }
            world.map_pos.xpos -= moveAmountl;
        } else {
            p.setSpeed("left", 0);
        }
    }

    public void moveMapLeftGlide(float moveAmountl) {
        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos - moveAmountl),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (p.getSpeed("left") != 0) {
                p.setSpeed("left", p.getSpeed("left") - p.getSlowdown());

                if (p.getSpeed("left") < 0) {
                    p.setSpeed("left", 0);
                }
            }
            world.map_pos.xpos -= moveAmountl;
        } else {
            p.setSpeed("left", 0);
        }
    }

    public void moveMapRight(float moveAmountr) {
        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (p.getSpeed("right") < p.getMaxSpeed()) {
                p.setSpeed("right", p.getSpeed("right") + p.getSlowdown());
            } else {
                p.setSpeed("right", p.getMaxSpeed());
            }
            world.map_pos.xpos += moveAmountr;
        } else {
            p.setSpeed("right", 0);
        }
    }

    public void moveMapRightGlide(float moveAmountr) {
        if (!player.chk.CollisionPlayerBlock(
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos)),
                new Point((int) (pos.xpos + world.map_pos.xpos + width + moveAmountr),
                        (int) (pos.ypos + world.map_pos.ypos + height))
        )) {
            if (p.getSpeed("right") != 0) {
                p.setSpeed("right", p.getSpeed("right") + p.getSlowdown());

                if (p.getSpeed("right") < 0) {
                    p.setSpeed("right", 0);
                }
            }
            world.map_pos.xpos += moveAmountr;
        } else {
            p.setSpeed("right", 0);
        }
    }

    public void drawLifeBar(Graphics2D g) {
        g.setColor(new Color((int) (player.getLifePoints() * (255 / player.getLifePoints())), (int) (player.getLifePoints() * (68 / player.getLifePoints())), (int) (player.getLifePoints() * (68 / player.getLifePoints()))));
        g.fillRect(10, Main.height - 35, (int) (player.getLifePoints() * (100 / player.getLifePoints())), 30);
        if (player.getLifePoints() > ((player.getPlayerLevel() + 1) * 100 / player.getPlayerLevel() + 5) / 2) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString("" + (int) player.getLifePoints(), 129 / 2 - 3 * 4, Main.height - 17);
        g.setColor(Color.WHITE);
        g.drawRect(10, Main.height - 35, 100, 30);
    }

}
