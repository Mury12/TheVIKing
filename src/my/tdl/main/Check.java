/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.main;

import java.awt.Point;
import my.tdl.generator.BlockModel;
import my.tdl.generator.TileManagerController;
import my.tdl.generator.World;

/**
 *
 * @author Andre
 */
public class Check {
    
  
    /**
     * This function is responsible for checking the collision to the next blocks.
     * This means that the character cant pass through a solid block so a collision is
     * treated as a non-passing through block. The player's speed will be set to 0 a he
     * will cannot pass over the block.
     * @param w the current world map
     * @param direction the direction moved. up, down, left and right are the options.
     * @return boolean that indicates the pass-through property.
     */
    public boolean hasColided(World w, String direction){
        if(w.getNextBlock(direction).isSolid()){
            return true;
        }
        return false;
    }
    
}
