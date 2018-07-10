package my.tdl.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;
import my.tdl.MoveableObjects.Player;

/**
 *
 * @author Andre
 */
public class TileManager {
    
    public static ArrayList<BlockModel> blocks = new ArrayList<>(); //cria um array de blocos para renderizar
    public static ArrayList<BlockModel> loaded_blocks = new ArrayList<>();
    private World world;
    
    public TileManager(){
        
    }
    
    public TileManager(World world){
        this.world = world;
    }
    
    public void tick(double deltaTime) {
        for (BlockModel block : blocks) {
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
        for(BlockModel block : blocks){
            block.render(g);
        }
    }

    public static ArrayList<BlockModel> getBlocks() {
        return blocks;
    }

    public static ArrayList<BlockModel> getLoaded_blocks() {
        return loaded_blocks;
    }
    
}
