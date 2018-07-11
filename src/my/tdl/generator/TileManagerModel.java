/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.generator;

import java.util.ArrayList;

/**
 *
 * @author andremury
 */
public class TileManagerModel {

    public BlockController blocks;
    public BlockController loaded_blocks;
    private World world;

    public TileManagerModel() {
        this.blocks = new BlockController();
        this.loaded_blocks = new BlockController();
    }

    public TileManagerModel(World world) {
        this.blocks = new BlockController();
        this.loaded_blocks = new BlockController();
        this.world = world;
    }

    public void tick(double deltaTime) {
        for (BlockModel block : blocks.getBlockModel()) {
            block.tick(deltaTime);

            if (world.getPlayer().render.intersects(block)) {
                block.setAlive(true);
                if (!loaded_blocks.getBlockModel().contains(block)) {
                    loaded_blocks.addBlockToModel(block);
                }
            } else {
                if (loaded_blocks.getBlockModel().contains(block)) {
                    loaded_blocks.removeBlockFromModel(block);
                }
                block.setAlive(false);
            }
            if (!world.getPlayer().isDebugging()) {
                if (!loaded_blocks.isBlockModelEmpty()) {
                    loaded_blocks.clearBlockModel();
                }
            }
        }
    }

    public BlockController getBlocks() {
        return this.blocks;
    }

    public BlockController getLoaded_blocks() {
        return this.loaded_blocks;
    }

    void setWorld(World world) {
        this.world = world;
    }

}
