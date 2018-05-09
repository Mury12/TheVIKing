/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;
import my.tdl.MoveableObjects.Player;

/**
 *
 * @author Andre
 */
public class TileManager {
    
    public static ArrayList<Block> blocks = new ArrayList<>(); //cria um array de blocos para renderizar
    public static ArrayList<Block> loaded_blocks = new ArrayList<>();
    private World world;
    
    public TileManager(){
        
    }
    
    public TileManager(World world){
        this.world = world;
    }
    
    public void tick(double deltaTime) {
        for (Block block : blocks) {
            block.tick(deltaTime);

            if (world.getPlayer().render.intersects(block)) {
                block.setAlive(true);
                    if(!loaded_blocks.contains(block)){
                        loaded_blocks.add(block);
                    }
            } else {
                if (loaded_blocks.contains(block)) {
                    loaded_blocks.remove(block);
                }
                block.setAlive(false);
            }
            if(!world.getPlayer().isDebugging()){
                if(!loaded_blocks.isEmpty()){
                    loaded_blocks.clear();
                }
            }
        }
    }
    
    public void render(Graphics2D g){
        for(Block block : blocks){
            block.render(g);
        }
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    public static ArrayList<Block> getLoaded_blocks() {
        return loaded_blocks;
    }
    
}
