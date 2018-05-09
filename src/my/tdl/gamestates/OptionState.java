/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.gamestates;

import java.awt.Graphics2D;
import my.tdl.gamestate.GameState;
import my.tdl.gamestate.GameStateButton;
import my.tdl.gamestate.GameStateManager;
import my.tdl.main.Main;
import my.tdl.managers.Mousemanager;

/**
 *
 * @author Andre
 */
//Essa classe gerará o mapa
public class OptionState extends GameState{
    
    GameStateButton graphics;
    GameStateButton quality;
    GameStateButton sound;
    GameStateButton music;
    GameStateButton back;
    Mousemanager mm;
    private int bth = 200;
    
    public OptionState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void init() {
        mm = new Mousemanager();
        graphics = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), "Graphics");
        quality = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), "Quality");
        sound = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), "Sound Vol");
        music = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), "Music Vol");
        back = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), new MenuState(gsm), gsm, "Back to Menu");
    }

    public float btnAlignmentH(ALIGN p){
        switch(p){
            case CENTER:
                return Main.width/2 - (float)(GameStateButton.getBtnWidth()/2);
            case LEFT:
                return 0;
            case RIGHT:
                return Main.width - (float)GameStateButton.getBtnWidth();
            default:
                return 0;
        }
    }
    
    public float btnAlignmentV(){
        
        this.bth += GameStateButton.getBtnHeight() + 10;
        
        return this.bth;
    }
    
    @Override
    public void tick(double deltaTime) {
        mm.tick();
        graphics.tick();
        quality.tick();
        back.tick();
        music.tick();
        sound.tick();
    }
    
    @Override
    public void render(Graphics2D g) {
        graphics.render(g);
        quality.render(g);
        back.render(g);
        music.render(g);
        sound.render(g);
        g.drawImage(MenuState.head_image, 0, 50, Main.width, 200, null);
        mm.render(g);
    }
    public enum ALIGN{
        CENTER,
        LEFT,
        RIGHT,
    }
}
