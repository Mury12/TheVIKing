/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.generator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import my.tdl.MoveableObjects.Player;
import my.tdl.main.Main;

/**
 *
 * @author Andre
 */
public class ChatBox {

    public static boolean typed;
    public static boolean backspace;
    public static String message="";
    public static String _message="";
    private static char lastTyped;
    public static boolean space;
    private static char a;
    public static Graphics2D g1;
    public static double lastTimer;
    public static ArrayList<String> chatLog = new ArrayList<>();
    static Font font = new Font("Arial", 10, 13);
    
    public static void drawChatBox(Graphics2D g){
        g.drawRect(10, (int)(Main.height-Main.height*1.5/3 -1), Main.width/4, Main.height/3);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.fillRect(10, (int)(Main.height-Main.height*1.5/3 -1), Main.width/4, Main.height/3);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g.fillRect(10, (int)(Main.height-Main.height/6 - 21), Main.width/4, 20);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g1 = g;
    }
    
    public static void writeMessage(char key){
        
        if(typed){
            if(key != lastTyped){
                space = false;
                message += key;
                typed = false;
                if(backspace){
                    message = message.substring(0, message.length()-1);
                    backspace = false;
                    System.out.println(message);
                }
                if(space){
                    lastTyped = key;
                }
            }else{
                message = message;
            }
        }
        if(!Player.message_sent && message.length()>1){
            g1.setColor(Color.BLACK);
            g1.setFont(font);
            g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            g1.drawString(message+"", 10, Main.height-Main.height/6-10);
            g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
            g1.setColor(Color.WHITE);
        }
    }
    
    public static void sendMessage(){
        if(lastTimer > 1 && lastTimer < 350){
            if(lastTimer >=2 && lastTimer <4){
                clearChatString();
            }
            MessageBaloon.getFinalMessage(_message, g1);
        }else{
            if(lastTimer >= 200){
                clearBaloonString();
                clearChatString();
                Player.message_sent = false;
            lastTimer = 0;
            }
        }
    }
    
    private static void clearChatString(){
        message = "";
    }
    private static void clearBaloonString(){
        _message = "";
    }

    public static void writeOnChatBox() {
            for(int i=0; i<chatLog.size(); i++){
                g1.setFont(new Font("Arial", 10, 13));
                g1.setColor(Color.BLACK);
                g1.drawString(chatLog.get(i), 15, (int)(Main.height-Main.height*1.5/3 -1)+15+i*13);
                g1.setColor(Color.WHITE);
            }
        }
    
    public static Font getFont(){
        return font;
    }
    
}
