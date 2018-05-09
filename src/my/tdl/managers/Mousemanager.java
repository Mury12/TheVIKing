/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.managers;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import my.tdl.generator.World;
import my.tdl.main.Assets;

/**
 *
 * @author Andre
 */
public class Mousemanager implements MouseListener, MouseMotionListener, MouseWheelListener{
    
    
    private static int mouseMovedX, mouseMovedY;
    public static Point mouse;
    
    public static boolean pressed;
    public static boolean released;
    
    public void tick(){
        mouse = new Point(mouseMovedX, mouseMovedY);
        
        getActions();
            
    }
    
    //getters pras actions do mouse pelo HUDmanager
    public void getUpAction(){
        if(HUDmanager.getUpPol()!=null){
            if(HUDmanager.getUpPol().contains(mouse)){
                if(pressed){
                    
                }
            }
        }
    }
    public void getDownAction(){
        if(HUDmanager.getDownPol()!=null){
            if(HUDmanager.getDownPol().contains(mouse)){
                if(pressed){
                    
                }
            }
        }
    }
    public void getLeftAction(){
        if(HUDmanager.getLeftPol()!=null){
            if(HUDmanager.getLeftPol().contains(mouse)){
                if(pressed){

                }
            }
        }
    }
    public void getRightAction(){
        if(HUDmanager.getRightPol()!=null){
            if(HUDmanager.getRightPol().contains(mouse)){
                if(pressed){

                }
            }
        }
    }
    public void getActions(){
        
    }
    
    public void render(Graphics2D g){
        //g.fillRect(mouseMovedX, mouseMovedY, 4, 4);
        if(pressed){
            g.drawImage(Assets.getMousePressed(), mouseMovedX, mouseMovedY, 32, 32, null);
        }else{
            g.drawImage(Assets.getMouseUnpressed(), mouseMovedX, mouseMovedY, 32, 32, null);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            released = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
        pressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            pressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMovedX = e.getX();
        mouseMovedY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseMovedX = e.getX();
        mouseMovedY = e.getY();
        released = false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}
