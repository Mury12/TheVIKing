/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.managers;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import my.tdl.MoveableObjects.Player;
import my.tdl.generator.ChatBox;
import static my.tdl.generator.ChatBox.g1;
import sun.font.FontDesignMetrics;

/**
 *
 * @author Andre
 */
public class GUImanager {

    
    public GUImanager(){
        
    }
    
    public void render(Graphics2D g){
        if(Player.isChatting()){
            ChatBox.drawChatBox(g);
            if(Player.isTyping()){
                    ChatBox.writeMessage(Player.getKeyTyped());
                if(Player.message_sent){
                    ChatBox.sendMessage();
                    ChatBox.lastTimer++;
                }
            }
            ChatBox.writeOnChatBox();
        }
    }
}
