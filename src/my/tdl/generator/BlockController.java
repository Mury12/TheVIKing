package my.tdl.generator;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import my.project.gop.main.Vector2F;
import my.tdl.generator.BlockModel;
import my.tdl.generator.BlockView;

/**
 *
 * @author andremury
 */
public class BlockController {

    private CopyOnWriteArrayList<BlockView> blockEnts;
    private BlockModel spawn;
    private BlockView blockView;
    private ArrayList<BlockModel> blockModel;

    public BlockController(){
        this.blockEnts = new CopyOnWriteArrayList<>();
    }
        //View
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
        return this.blockEnts;
    }

    public boolean isEntEmpty() {
        return this.blockEnts.isEmpty();
    }
    
    //Model
    public int getBlockSize(){
        return BlockModel.BlockSize;
    }
    public BlockModel getSpawn() {
        return spawn;
    }

    public void setSpawn(float yPos, float xPos) {
        spawn = new BlockModel(new Vector2F(xPos*getBlockSize(), yPos*getBlockSize()));

    }

    void clearSpawn() {
        this.spawn = null;
    }
    
    public BlockModel newBlockModel(Vector2F pos, BlockModel.BlockType blocktype){
        return new BlockModel(pos, blocktype);
    }
   
    
}
