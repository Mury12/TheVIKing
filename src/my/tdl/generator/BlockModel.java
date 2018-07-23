package my.tdl.generator;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import my.project.gop.main.Vector2F;
import my.tdl.main.Assets;

import java.util.ArrayList;
import my.tdl.main.Animator;

/**
 *
 * @author Andre
 */
public class BlockModel extends Rectangle {

    /**
     * This variable sets the world position of a tile or block.
     * @pos
     */
    public Vector2F pos = new Vector2F();
    /**
     * This variable sets the block size in pixels.
     * Default: 48
     * @BlockSize 
     */
    public static int BlockSize = 48; //tamanho do block na tela
    /**
     * This variable sets the block type by its BlockType name.
     * @blocktype
     */
    public BlockType blocktype;
    /**
     * This variable loads the sprite of a block or tile.
     * @block
     */
    private BufferedImage block;
    /**
     * This variable sets if the block is solid or not.
     * @isSolid
     */
    private boolean isSolid;
    /**
     * This variable sets if the block is rendered or not.
     * @isAlive
     */
    private boolean isAlive;
    /**
     * This variable sets if the block is unloaded or not.
     * @droped
     */
    private boolean droped = false;

    private ArrayList<BufferedImage> listWallTorch;
    Animator ani_torch;

    public BlockModel(Vector2F pos) {
        setBounds((int) pos.xpos, (int) pos.ypos, BlockSize, BlockSize);
        this.pos = pos;
        isAlive = true;
    }

    public BlockModel(Vector2F pos, BlockType blocktype) {
        setBounds((int) pos.xpos, (int) pos.ypos, BlockSize, BlockSize);
        this.pos = pos;
        isAlive = true;
        this.blocktype = blocktype;
        init();
    }

    /**
     * This function is responsible to set the solidness of a block.
     * Passing true means that the block is solid and the collision system
     * will prevent the passing trough otherwise will be a normal tile.
     * @param isSolid boolean in true for solid and false for tile
     */
    public void isSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }
    /**
     * This is responsible to return if a block is solid.
     * This function is used to set the collision system.
     * @return boolean for solidness of a block.
     */
    public boolean isSolid(){
        return this.isSolid;
    }

    public void init() {
        //Retorna os blocos contidos na spritesheet
        getBlockType();
    }

    public void tick(double deltaTime) {
        if (isAlive) {
            setBounds((int) pos.xpos, (int) pos.ypos, BlockSize, BlockSize);
        }
    }
    /**
     * This renders the block.
     * The g element must be passed as the same since the first of type.
     * @param g Graphics2D object set at the beginning.
     */
    public void render(Graphics2D g) {
        if (isAlive) {
            if (block != null) {
                g.drawImage(block, (int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos, BlockSize, BlockSize, null);
            } else {
                g.fillRect((int) pos.getWorldLocation().xpos, (int) pos.getWorldLocation().ypos, width, height);
            }
        }
    }
    /**
     * This function is responsible to get the sprite of a block.
     * Calling this function after setting blocktype element will convert it into 
     * an block image to render in the map.
     */
    public void getBlockType() {
        switch (this.blocktype) {
            //FLOORS
            case STONE_1:
                block = Assets.getStone_1();
                break;
            case VOID_1:
                block = Assets.getVoid_1();
                break;
            case SAND_1:
                block = Assets.getSand_1();
                break;
            case WOOD_1:
                block = Assets.getWood_1();
                break;
            case WOOD_2_V:
                block = Assets.getWood_2_V();
                break;
            case GRASS_1:
                block = Assets.getGrass_1();
                break;
            case DIRT_1:
                block = Assets.getDirt_1();
                break;
            case SPAWN_POS:
                block = Assets.getSpawn_pos();
                break;

            //WALLS
            case WALL_1:
                block = Assets.getWall_1();
                break;
            case WALL_1_TORCH:
                block = Assets.getWall_1_torch1();
                break;
            case WALL_1_BROWN:
                block = Assets.getWall_1_brown();
                break;
            case WALL_1_WINE:
                block = Assets.getWall_1_wine();
                break;
            case WALL_1_WINDOW:
                block = Assets.getWall_1_window();
                break;
            case WALL_1_ROOF:
                block = Assets.getWall_1_roof();
                break;
        }
    }
    /**
     * This enumerates the type of the block tile.
     * It's easier to chose the block if there's a name for each one
     * giving the versatility to name all the blocks instead of color codes. 
     */
    public enum BlockType {
        //FLOORS
        STONE_1,
        SAND_1,
        DIRT_1,
        VOID_1,
        WOOD_1,
        WOOD_2_V,
        GRASS_1,
        SPAWN_POS,
        //WALLS
        WALL_1,
        WALL_1_TORCH,
        WALL_1_BROWN,
        WALL_1_WINDOW,
        WALL_1_WINE,
        WALL_1_ROOF,
        
        //NOTFOUND
        NOT_FOUND,

    }

    //define os blocks
    /**
     * This function returns the living state of a block object.
     * This means that returns true if it is rendered and false otherwise.
     * @return boolean for a rendered block
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * This function represents if the block exists in world.
     * isAlive is a variable that stores a living state of the block,
     * setting up the unique block object that exists in rendered in the map.
     * @param isAlive set as true if rendered.
     */
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    /**
     * This function is responsible for getting the position of an existing block.
     * @return Vector2F object.
     */
    public Vector2F getBlockLocation() {
        return pos;
    }
    /**
     * This function is responsible to return the block tile name.
     * This means that the type of the selected block of Blocktype be known.
     * @return String containing the tile name of the block.
     */
    public String getBlockTypeName() {
        return blocktype.toString();
    }
}
