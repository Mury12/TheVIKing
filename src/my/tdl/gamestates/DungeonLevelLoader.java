/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.gamestates;

import java.awt.Graphics2D;
import my.tdl.MoveableObjects.Player;
import my.tdl.gamestate.GameState;
import my.tdl.gamestate.GameStateManager;
import my.tdl.generator.World;

/**
 *
 * @author Andre
 */
//Essa classe gerará o mapa
public class DungeonLevelLoader extends GameState{
    
    boolean exit = false;
    public static World world;
    private String worldName;
    private String map_name;
    
    public DungeonLevelLoader(GameStateManager gsm){
        super(gsm);
    }
    public DungeonLevelLoader(GameStateManager gsm, String worldname, String map_name){
        super(gsm);
        this.worldName = worldname;
        this.map_name = map_name;
    }
    
    
    @Override
    public void init() {
        if(worldName==null){
            worldName = "NULL";
            map_name = "map";
        }
        world = new World(worldName, gsm);
        world.setWorldSize(50, 50);
        world.setWorldSpawn(3,3);
        world.addPlayer(new Player());
        world.init();
        world.generateWorld(map_name+".png");
    }

    @Override
    public void tick(double deltaTime) {
        if(world.hasGenerated){
            world.tick(deltaTime);
        }
    }

    @Override
    public void render(Graphics2D g) {
        if(world.hasGenerated){
            world.render(g);
        }
    }
    
}
