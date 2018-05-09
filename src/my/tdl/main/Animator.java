/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Andre
 */
public class Animator {
    
    private ArrayList<BufferedImage> frames;
    private volatile boolean running = false;
    public BufferedImage sprite;
    private long prevTime, speed;
    private int frameatPause, currentFrame;
    
    
    
    public Animator(ArrayList<BufferedImage> frames){
        this.frames = frames;
    }
    
    public void setSpeed(long speed){
        this.speed = speed;
    }

    public void update(long time) {
        if (running) {
            if (speed <= time - prevTime) {
                currentFrame++;
                try {
                    sprite = frames.get(currentFrame);
                } catch (IndexOutOfBoundsException e) {
                    reset();
                    sprite = frames.get(currentFrame);
                }
            }
        }
    }
    
    public void play(){
        running = true;
        prevTime = 0;
        frameatPause = 0;
        currentFrame = 0;
    }
    
    public void stop(){
        running = false;
        prevTime = 0;
        frameatPause = 0;
        currentFrame = 0;
    }
    
    public void pause(){
        frameatPause = currentFrame;
        running = false;
    }
    
    public void resume(){
        currentFrame = frameatPause;
    }
    
    public void reset(){
        currentFrame = 0;
    }
    
    public boolean isDoneAnimating(){
        if(currentFrame == frames.size()){
            return true;
        }else return false;
    }
}
