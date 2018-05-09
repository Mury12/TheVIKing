/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import my.project.gop.main.Vector2F;
import my.tdl.main.Assets;
import my.tdl.managers.Mousemanager;

/**
 *
 * @author Andre
 */
public class GameStateButton extends Rectangle{
    
    private Vector2F pos = new Vector2F();
    private GameState gamestate;
    private GameStateManager gsm;
    private boolean isHeldOver;
    private static int width = 32*7;
    private static int height = 64;
    private BufferedImage defaultImage;
    private String buttonMessage;
    
    public GameStateButton(float xpos, float ypos, GameState gamestate, GameStateManager gsm, String buttonMessage) {
        this.pos.xpos = xpos;
        this.pos.ypos = ypos;
        this.gamestate = gamestate;
        this.gsm = gsm;
        this.buttonMessage = buttonMessage;
        setBounds((int)pos.xpos, (int)pos.ypos, width, height);
        defaultImage = Assets.getButton_notover();
    }
    
    public GameStateButton(float xpos, float ypos, String buttonMessage){
        this.pos.xpos = xpos;
        this.pos.ypos = ypos;
        this.buttonMessage = buttonMessage;
        setBounds((int)pos.xpos, (int)pos.ypos, width, height);
        defaultImage = Assets.getButton_notover();
    }
    
    public void tick(){
        setBounds((int)pos.xpos, (int)pos.ypos, width, height);
        
        if(getBounds().contains(Mousemanager.mouse)){ //verifica a intersecção do mouse com o botão (getBounds pega a posição e o tamanho do botao e compara com as coordenadas do mouse)
            isHeldOver = true;
        } else  isHeldOver = false;
        
        if(isHeldOver){
            if(defaultImage != Assets.getButton_heldover()){
                defaultImage = Assets.getButton_heldover();
            }
            if(Mousemanager.pressed && defaultImage != Assets.getButton_clicked()){
                defaultImage = Assets.getButton_clicked();
            }
            
        }else{
            if(defaultImage != Assets.getButton_notover()){
                defaultImage = Assets.getButton_notover();
            }
        }
        //Checa a existencia do gamestate
        if(gamestate != null){
            if(isHeldOver){
                if(isPressed()){
                    gsm.states.push(gamestate); //chama o gamestate que o botão referencia
                    gsm.states.peek().init();
                    isHeldOver = false;
                    Mousemanager.released = false;
                }
            }
        }
    }
    
    Font font = new Font("Basic Map", 10, 30); //"cria" um modelo de fonte (texto)
    
    public void render(Graphics2D g){
        g.drawImage(defaultImage, (int)pos.xpos, (int)pos.ypos, width, height, null);
        g.setFont(font); //Seta a fonte a ser utilizada pelos textos abaixo dela.
        AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
        int tw = (int) font.getStringBounds(buttonMessage, frc).getWidth();
        g.drawString(buttonMessage, pos.xpos+ width/2 - tw/2, pos.ypos + height /2 + 8);
        
    }
    
    public boolean isPressed(){
        return Mousemanager.released;
    }
    public boolean isHeldOver(){
        return isHeldOver;
    }
    
    public static float getBtnWidth(){
        
        return width;
    }
    
    public static float getBtnHeight(){
        
        return height;
    }
    
}
