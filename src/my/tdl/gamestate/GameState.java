/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.gamestate;

import java.awt.Graphics2D;

/**
 *
 * @author Andre
 */
public abstract class GameState {
    
    public GameStateManager gsm;
    
    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }
    public GameState(){}
    public abstract void init();
    public abstract void tick(double deltaTime);
    public abstract void render(Graphics2D g);
}
