/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.generator;

import java.awt.Graphics2D;

/**
 *
 * @author andremury
 */
public class TileManagerView {

    private BlockController blocks;

        public void render(Graphics2D g){
        for(BlockModel block : blocks.getBlockModel()){
            block.render(g);
        }
    }
}
