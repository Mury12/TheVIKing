/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author andremury
 */
public class TileManagerController {

    private TileManagerModel tileModel;
    private TileManagerView tileView;
    
    public ArrayList<BlockModel> getBlocks() {
        return this.tileModel.getBlocks().getBlockModel();
    }
    
    public BlockController getBlocksController(){
        return this.tileModel.getBlocks();
    }

    public ArrayList<BlockModel> getLoaded_blocks() {
        return this.tileModel.getLoaded_blocks().getBlockModel();
    }

    public void setTileModel(World world) {
        this.tileModel = new TileManagerModel(world);
    }
    
    public void tick(double deltaTime){
        this.tileModel.tick(deltaTime);
    }
    public void render(Graphics2D g){
        this.tileView.render(g);
    }
    
}
