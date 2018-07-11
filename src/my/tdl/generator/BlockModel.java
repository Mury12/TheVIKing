package my.tdl.generator;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import my.project.gop.main.Vector2F;
import my.tdl.main.Assets;

import java.awt.Color;
import java.util.ArrayList;
import my.tdl.main.Animator;

/**
 *
 * @author Andre
 */
public class BlockModel extends Rectangle{
    
    public Vector2F pos = new Vector2F();
    public static int BlockSize = 48; //tamanho do block na tela
    private BlockType blocktype;
    private BufferedImage block;
    private boolean isSolid;
    private boolean isAlive;
    private boolean droped = false;
    
    private ArrayList<BufferedImage> listWallTorch;
    Animator ani_torch;
    
    public BlockModel(Vector2F pos) {
        setBounds((int)pos.xpos, (int)pos.ypos, BlockSize, BlockSize);
        this.pos = pos;
        isAlive = true;
    }
    
    public BlockModel(Vector2F pos, BlockType blocktype){
        setBounds((int)pos.xpos, (int)pos.ypos, BlockSize, BlockSize);
        this.pos = pos;
        isAlive = true;
        this.blocktype = blocktype;
        init();
    }


    //retorna se o bloco é solido ou não
    public BlockModel isSolid(boolean isSolid){
        this.isSolid = isSolid;
        return this;
    }
    public void init(){
        //Retorna os blocos contidos na spritesheet
        switch(blocktype){
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
    
    public void tick(double deltaTime){
        if(isAlive)
        setBounds((int)pos.xpos, (int)pos.ypos, BlockSize, BlockSize);
    }
    
    public void render(Graphics2D g){
        if(isAlive){
            if(block != null){
            //g.drawRect((int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize);
            g.drawImage(block, (int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, BlockSize, BlockSize, null);
            }else{
                //g.fillRect((int)pos.getWorldLocation().xpos, (int)pos.getWorldLocation().ypos, width, height);
            }
        }else{
            if(!droped){
                float xpos = pos.xpos + 24-12;
                float ypos = pos.ypos + 24-12;
                
                Vector2F newpos = new Vector2F(xpos, ypos);
                
                //World.dropBlockEntity(newpos, block);
                
                droped = true;
            }
        }
    }
   
    public enum BlockType{ 
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
        
    }
    //define os blocks
    public boolean isSolid() {
        return isSolid;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
    public Vector2F getBlockLocation(){
        return pos;
    }
    
}
