package my.tdl.generator;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import my.project.gop.main.Vector2F;
/**
 *
 * @author andremury
 */
public class BlockController {

    private CopyOnWriteArrayList<BlockView> blockEnts;
    private BlockModel spawn;
    private BlockView blockView;
    public  ArrayList<BlockModel> blockModel;

    public BlockController(){
        this.blockEnts = new CopyOnWriteArrayList<>();
        this.blockModel = new ArrayList<>();

    }
        //View
    public void setBlockEnts(CopyOnWriteArrayList blockents) {
        this.blockEnts = blockents;
    }

    public boolean addEntityToBlock(BlockView ent) {
        return this.blockEnts.add(ent);
    }

    public boolean removeEntityFromBlock(BlockView ent) {
        return this.blockEnts.remove(ent);
    }

    public void clearBlockEntities(){
        this.blockEnts.clear();
    }
    
    public CopyOnWriteArrayList<BlockView> getBlockEnts() {
        return this.blockEnts;
    }

    public BlockView getBlockView() {
        return this.blockView;
    }

    public boolean addBlockToModel(BlockModel block){
        return this.blockModel.add(block);
    }
    
    public boolean removeBlockFromModel(BlockModel block){
        return this.blockModel.remove(block);
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

    public ArrayList<BlockModel> getBlockModel() {
        return this.blockModel;
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

    boolean isBlockModelEmpty() {
        return this.blockModel.isEmpty();
    }

    void clearBlockModel() {
        this.blockModel.clear();
    }
   
    
}
