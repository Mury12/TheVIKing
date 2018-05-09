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
public class QuitState extends GameState{
    
    GameStateButton yes;
    GameStateButton no;

    Mousemanager mm;
    private int bth = 200;
    
    public QuitState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    public void init() {
        mm = new Mousemanager();
        yes = new GameStateButton(btnAlignmentH(ALIGN.CENTER)-GameStateButton.getBtnWidth()/2, Main.height/2+20, "Yes");
        no = new GameStateButton(btnAlignmentH(ALIGN.CENTER)+GameStateButton.getBtnWidth()/2 + 10, Main.height/2+20, "No");
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
        yes.tick();
        no.tick();

        if(yes.isHeldOver()){        
            if(yes.isPressed()){
                System.exit(1);
            }
        }
        if(no.isHeldOver()){
            if(no.isPressed()){
                gsm.states.push(new MenuState(gsm)); //Ir para algum GameState
                gsm.states.peek().init(); //iniciar o gamestate
            }
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        yes.render(g);
        no.render(g);
        g.drawImage(MenuState.head_image, 0, 50, Main.width, 200, null);
        g.drawString("Are you sure quitting this AWESOME MASTERPIECE of game?", Main.width/9, Main.height/2 - 50);
        mm.render(g);
    }
    public enum ALIGN{
        CENTER,
        LEFT,
        RIGHT,
    }
}
