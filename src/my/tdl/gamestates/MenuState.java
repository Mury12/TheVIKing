/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.gamestates;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import my.project.gop.main.loadImageFrom;
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
public class MenuState extends GameState{
    
    GameStateButton startGame;
    GameStateButton multiplayer;
    GameStateButton options;
    GameStateButton quit;
    Mousemanager mm;
    private int bth = 200;
    public static BufferedImage head_image;
    
    
    public MenuState(GameStateManager gsm){
        super(gsm);
        head_image = loadImageFrom.LoadImageFrom(Main.class, "THE VI KING LOGO.png");
    }

    @Override
    public void init() {
        mm = new Mousemanager();
        startGame = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), new DungeonLevelLoader(gsm), gsm, "Start Game");
        multiplayer = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), new DungeonLevelLoader(gsm), gsm, "Multiplayer");
        options = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), new OptionState(gsm), gsm, "Options");
        quit = new GameStateButton(btnAlignmentH(ALIGN.CENTER), btnAlignmentV(), new QuitState(gsm), gsm, "Quit");
    }

    public static float btnAlignmentH(ALIGN p){
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
        startGame.tick();
        quit.tick();
        options.tick();
        multiplayer.tick();
    }
    
    @Override
    public void render(Graphics2D g) {
        startGame.render(g);
        quit.render(g);
        options.render(g);
        multiplayer.render(g);
        g.drawImage(head_image, 0, 50, Main.width, 200, null);
        mm.render(g);
    }
    public enum ALIGN{
        CENTER,
        LEFT,
        RIGHT,
    }
}
