/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.main;

import java.awt.Point;
import my.tdl.generator.BlockModel;
import my.tdl.generator.TileManager;

/**
 *
 * @author Andre
 */
public class Check {
    
    public static boolean CollisionPlayerBlock(Point p1, Point p2){
        for(BlockModel block : TileManager.blocks.getBlockModel()){
            if(block.isSolid()){
                if(block.contains(p1) || block.contains(p2)){
                    return true;
                }
            }
        }
        return false;
    }
}
