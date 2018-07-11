package my.tdl.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;
import jdk.nashorn.internal.ir.Block;
import my.tdl.MoveableObjects.Player;

/**
 *
 * @author Andre
 */
public class TileManager {
    
    public static BlockController blocks = new BlockController();
    public static BlockController loaded_blocks = new BlockController();
    private World world;
    
    public TileManager(){
        
    }
    
    public TileManager(World world){
        this.world = world;
    }
    
    public void tick(double deltaTime) {
        for (BlockModel block : blocks.getBlockModel()) {
            block.tick(deltaTime);

            if (world.getPlayer().render.intersects(block)) {
                block.setAlive(true);
                    if(!loaded_blocks.getBlockModel().contains(block)){
                        loaded_blocks.addBlockToModel(block);
                    }
            } else {
                if (loaded_blocks.getBlockModel().contains(block)) {
                    loaded_blocks.removeBlockFromModel(block);
                }
                block.setAlive(false);
            }
            if(!world.getPlayer().isDebugging()){
                if(!loaded_blocks.isBlockModelEmpty()){
                    loaded_blocks.clearBlockModel();
                }
            }
        }
    }
    
    public void render(Graphics2D g){
        for(BlockModel block : blocks.getBlockModel()){
            block.render(g);
        }
    }

    public static ArrayList<BlockModel> getBlocks() {
        return blocks.getBlockModel();
    }

    public static ArrayList<BlockModel> getLoaded_blocks() {
        return loaded_blocks.getBlockModel();
    }
    
}
