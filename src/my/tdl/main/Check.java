/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.main;

import java.awt.Point;
import my.tdl.generator.BlockModel;
import my.tdl.generator.TileManagerController;

/**
 *
 * @author Andre
 */
public class Check {
    private TileManagerController tc;
    public boolean CollisionPlayerBlock(Point p1, Point p2){
        for(BlockModel block : tc.getBlocksController().getBlockModel()){
            if(block.isSolid()){
                if(block.contains(p1) || block.contains(p2)){
                    return true;
                }
            }
        }
        return false;
    }
}
