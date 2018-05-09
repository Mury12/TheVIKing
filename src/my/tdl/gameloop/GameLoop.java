/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.gameloop;

import my.project.gop.main.IDGameLoop;
import my.tdl.gamestate.GameStateManager;
import my.tdl.main.Assets;

/**
 *
 * @author Andre
 */
public class GameLoop extends IDGameLoop{
    
    public static GameStateManager gsm;
    public static Assets assets = new Assets();
    
    
    public GameLoop(int width, int height) {
        super(width, height);
    }

    @Override
    public void init() {
        assets.init();
        gsm = new GameStateManager();
        gsm.init();
        super.init();
    }
    
    @Override
    public void tick(double deltaTime) {

        gsm.tick(deltaTime);
    }
    
    @Override
    public void render() {
        super.render();
        gsm.render(graphics2D);
        clear();
    }

    @Override
    public void clear() {
        super.clear();
    }

}
