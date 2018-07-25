/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.managers;

import java.awt.event.KeyListener;
import my.tdl.MoveableObjects.Player;
import my.tdl.MoveableObjects.PlayerActions;
import my.tdl.MoveableObjects.PlayerAnimations;
import my.tdl.gamestates.DungeonLevelLoader;
import my.tdl.generator.ChatBox;
import my.tdl.main.Main;

/**
 *
 * @author andremury
 */
public class KeyManager implements KeyListener{
    Player p;
    public static int kp;
    
    public KeyManager(Player p){
        this.p = p;
    }
    public KeyManager(){}
    
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {

    }

    public static int getKp() {
        return kp;
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int key = e.getKeyCode(); //pega o código da tecla pressionada
        kp = key;
        if (key == java.awt.event.KeyEvent.VK_UP) {
            if (!Player.getPlayerActions().isMoving()) {
                Player.getPlayerActions().setMovingState(true);
            }
            PlayerAnimations.up = true;
        }
        if (key == java.awt.event.KeyEvent.VK_DOWN) {
            if (!Player.getPlayerActions().isMoving()) {
                Player.getPlayerActions().setMovingState(true);
            }
            PlayerAnimations.down = true;
        }
        if (key == java.awt.event.KeyEvent.VK_LEFT) {
            if (!Player.getPlayerActions().isMoving()) {
                Player.getPlayerActions().setMovingState(true);
            }
            PlayerAnimations.left = true;
        }
        if (key == java.awt.event.KeyEvent.VK_RIGHT) {
            if (!Player.getPlayerActions().isMoving()) {
                Player.getPlayerActions().setMovingState(true);
            }
            PlayerAnimations.right = true;
        }
        if (key == java.awt.event.KeyEvent.VK_SHIFT) {
            Player.getPlayerActions().setRunState(true);
        }
        if (key == java.awt.event.KeyEvent.VK_CONTROL) {
            PlayerActions.duck = true;
        }

        if (Player.getPlayerActions().isChatting()) {
            if (Player.getPlayerActions().isTyping()) {
                if (key == 8) {
                    ChatBox.backspace = true;
                }
                if (key >= 32 && key <= 122) {
                    if (key != java.awt.event.KeyEvent.VK_UP && key != java.awt.event.KeyEvent.VK_DOWN && key != java.awt.event.KeyEvent.VK_LEFT && key != java.awt.event.KeyEvent.VK_RIGHT) {
                        ChatBox.typed = true;
                    }
                    Player.getPlayerActions().setCharTyped(e.getKeyChar());
                    ChatBox.space = (key == 32);
                }
            }
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        int key = e.getKeyCode();

        if (key == java.awt.event.KeyEvent.VK_UP) {
            PlayerAnimations.up = false;
        }
        if (key == java.awt.event.KeyEvent.VK_DOWN) {
            PlayerAnimations.down = false;
        }
        if (key == java.awt.event.KeyEvent.VK_LEFT) {
            PlayerAnimations.left = false;
        }
        if (key == java.awt.event.KeyEvent.VK_RIGHT) {
            PlayerAnimations.right = false;
        }
        if (key == java.awt.event.KeyEvent.VK_SHIFT) {
            Player.getPlayerActions().setRunState(false);
        }
        if (key == java.awt.event.KeyEvent.VK_CONTROL) {
            PlayerActions.duck = false;
        }
        if (key == java.awt.event.KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
        if (key == java.awt.event.KeyEvent.VK_F3) {
            HUDmanager.debug = !HUDmanager.isDebugging();
        }
        if (key == java.awt.event.KeyEvent.VK_F12) {
            p.setPlayerLevel(1);
        }
        if (key == java.awt.event.KeyEvent.VK_P) {
            DungeonLevelLoader.world.changeLevel("World", "Map2");
        }
        if (key == java.awt.event.KeyEvent.VK_ENTER) {
            if (!p.getPlayerActions().isChatting()) {
                p.getPlayerActions().chatBox = true;
                System.out.println("Chat opened.");
            } else {
                if (p.getPlayerActions().isTyping()) {
                    p.getPlayerActions().message_sent = true;
                    ChatBox._message = ChatBox.message;
                    if (!ChatBox.chatLog.contains(ChatBox.message)) {
                        if (ChatBox.chatLog.size() * 13.5 >= Main.height / 3 - 33) {
                            ChatBox.chatLog.remove(0);
                        }
                        ChatBox.chatLog.add(ChatBox.message);
                    }
                    System.out.println("Message sent.");
                } else {
                    if (p.getPlayerActions().isChatting() && !p.getPlayerActions().isTyping()) {
                        p.getPlayerActions().typing = true;
                        System.out.println("Ready to type");
                    }
                }
            }
        }
        if (key == java.awt.event.KeyEvent.VK_F2) {
            if (p.getPlayerActions().isChatting()) {
                p.getPlayerActions().resetChat();
                System.out.println("Chat closed.");
            }
        }
    }
}
