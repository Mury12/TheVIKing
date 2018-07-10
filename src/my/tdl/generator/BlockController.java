package my.tdl.generator;

import java.util.concurrent.CopyOnWriteArrayList;
import my.tdl.generator.BlockModel;
import my.tdl.generator.BlockView;

/**
 *
 * @author andremury
 */
public class BlockController {

    public CopyOnWriteArrayList<BlockView> blockEnts;

    public BlockController(){
        this.blockEnts = new CopyOnWriteArrayList<>();
    }
    
    public void setBlockEnts(CopyOnWriteArrayList blockents) {
        this.blockEnts = blockents;
    }

    public void addEntityToBlock(BlockView ent) {
        this.blockEnts.add(ent);
    }

    public void removeEntityFromBlock(BlockView ent) {
        this.blockEnts.remove(ent);

    }

    public void clearBlockEntities(){
        this.blockEnts.clear();
    }
    
    public CopyOnWriteArrayList<BlockView> getBlockEnts() {
        return blockEnts;
    }

    public boolean isEntEmpty() {
        return this.blockEnts.isEmpty();
    }

}
