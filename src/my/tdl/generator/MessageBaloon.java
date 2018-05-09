/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.generator;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import my.project.gop.main.loadImageFrom;
import my.tdl.main.Main;

/**
 *
 * @author Andre
 */
public class MessageBaloon {
    
    private static BufferedImage msg_bgbody = loadImageFrom.LoadImageFrom(Main.class, "msg_bgbody.png");
    private static BufferedImage msg_bgtail = loadImageFrom.LoadImageFrom(Main.class, "msg_tailbg.png");
    private static BufferedImage msg_bgright = loadImageFrom.LoadImageFrom(Main.class, "msg_bgright.png");
    public static String msg;
    public static Graphics2D g;
    private static FontMetrics fm;
    public MessageBaloon() {
    }

    public MessageBaloon(String msg, Graphics2D g) {
        this.msg = msg;
        this.g = g;
        getFinalMessage(msg, g);
    }
    
    public static void getFinalMessage(String msg, Graphics2D g){
        int a = g.getFontMetrics(ChatBox.getFont()).stringWidth(msg);//retorna a largura da string em px
        if(ChatBox.lastTimer > 0 && ChatBox.lastTimer <=10){
            
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)ChatBox.lastTimer/10));
            g.setFont(new Font("Arial", 10, 13));
            g.drawImage(msg_bgtail, Main.width / 2 + 15, Main.height / 2 - 55, 31, 31, null);
            g.drawImage(msg_bgbody, Main.width / 2 + 46, Main.height / 2 - 55,  a, 29, null);
            g.drawImage(msg_bgright, Main.width / 2 + 31 + a + 5, Main.height / 2 - 55, 18, 29, null);
        }else{
            g.setFont(new Font("Arial", 10, 13));
            g.drawImage(msg_bgtail, Main.width / 2 + 15, Main.height / 2 - 55, 31, 31, null);
            g.drawImage(msg_bgbody, Main.width / 2 + 46, Main.height / 2 - 55, a, 29, null);
            g.drawImage(msg_bgright, Main.width / 2 + 31 + a + 5, Main.height / 2 - 55, 18, 29, null);
        }
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g.setColor(Color.BLACK);
        g.drawString(msg + "", Main.width / 2 + 37, Main.height / 2 - 35);
        g.setFont(new Font("Arial", 10, 10));
    }
    
    
}
