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
    /**
     * This functions sets up the block entities.
     * This means that what is in this block were loaded into the world
     * but went out the rendering area and its waiting for re-rendering.
     * @param blockents is a block array
     */
    public void setBlockEnts(CopyOnWriteArrayList blockents) {
        this.blockEnts = blockents;
    }
    /**
     * This function is responsible for adding a block into the entities array.
     * This means that the block were rendered in some moment but went out of the
     * rendering area and is waiting for re-rendering.
     * @param ent the BlockView object
     * @return a boolean that indicates the adding process success.
     */
    public boolean addEntityToBlock(BlockView ent) {
        return this.blockEnts.add(ent);
    }
    /**
     * This function is responsible for removing a block from the entities array.
     * @param ent the BlockView object wanted
     * @return a boolean that indicates the adding process success.
     */
    public boolean removeEntityFromBlock(BlockView ent) {
        return this.blockEnts.remove(ent);
    }
    /**
     * This function is responsible for the full cleaning of the block entities array.
     * This means that the block entities array will be fully unloaded.
     */
    public void clearBlockEntities(){
        this.blockEnts.clear();
    }
    /**
     * This function is responsible to return an array of block entities.
     * @return  BlockView array object.
     */
    public CopyOnWriteArrayList<BlockView> getBlockEnts() {
        return this.blockEnts;
    }
    /**
     * This function is responsible to get the rendered blocks.
     * @return a BlockView object.
     */
    public BlockView getBlockView() {
        return this.blockView;
    }
    /**
     * This function is responsible to add a block into the BlockModel array.
     * This blocks are rendered in the map.
     * @param block BlockModel object
     * @return boolean that indicates the process success.
     */
    public boolean addBlockToModel(BlockModel block){
        return this.blockModel.add(block);
    }
    /**
     * This function is responsible for removing a block from the BlockModel array.
     * This means that the block is out of the map and unloaded.
     * @param block BlockModel wanted
     * @return boolean that indicates the process success.
     */
    public boolean removeBlockFromModel(BlockModel block){
        return this.blockModel.remove(block);
    }
    /**
     * This function is responsible to indicate if the block entities array is empty.
     * @return true for empty and false for not empty.
     */
    public boolean isEntEmpty() {
        return this.blockEnts.isEmpty();
    }
    
    /**
     * This function is responsible to get the size of the block.
     * This is the size in pixels of the block defined in BlockModel class.
     * @return integer with the block size.
     */
    public int getBlockSize(){
        return BlockModel.BlockSize;
    }
    /**
     * This function is responsible to return the spawn block.
     * This means that there will be a unique spawn block that indicates
     * where the character will spawn in that map when he comes to it.
     * @return BlockModel object.
     */
    public BlockModel getSpawn() {
        return spawn;
    }
    /**
     * This function is responsible to get the block model array.
     * This will return an ArrayList of BlockModel objects containing 
     * all the blocks loaded in the map.
     * @return ArrayList of BlockModel objects.
     */
    public ArrayList<BlockModel> getBlockModel() {
        return this.blockModel;
    }
    /**
     * This function is responsible for setting the player's spawn point.
     * This means that the player will spawn in this point.
     * @param yPos floating point position in Y axis.
     * @param xPos floating point position in X axis.
     */
    public void setSpawn(float yPos, float xPos) {
        spawn = new BlockModel(new Vector2F(xPos*getBlockSize(), yPos*getBlockSize()));
    }
    /**
     * This function will clear the spawn point position.
     * This means that there will no longer have a spawn point
     * so a new spawn point needs to be set.
     */
    void clearSpawn() {
        this.spawn = null;
    }
    /**
     * This function is responsible for creating a new BlockModel object.
     * This means that this will return a set BlockModel object to the main 
     * process: set up a BlockModel array to be loaded into the map.
     * @param pos world position of the block.
     * @param blocktype name of the block.
     * @return a brand new set BlockModel object.
     */
    public BlockModel newBlockModel(Vector2F pos, BlockModel.BlockType blocktype){
        return new BlockModel(pos, blocktype);
    }
    /**
     * This function is responsible to indicate if the BlockModel array is empty.
     * @return true for empty and false for not empty.
     */
    boolean isBlockModelEmpty() {
        return this.blockModel.isEmpty();
    }
    /**
     * This function is responsible for the full cleaning of the BlockModel array.
     * This means that the BlockModel array will be fully unloaded.
     */
    void clearBlockModel() {
        this.blockModel.clear();
    }
   
    
}
