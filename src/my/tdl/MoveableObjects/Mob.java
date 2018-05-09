/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.MoveableObjects;

import java.awt.Graphics2D;
import my.project.gop.main.Vector2F;
import static my.tdl.generator.Block.BlockSize;
import my.tdl.generator.World;

/**
 *
 * @author Andre
 */
abstract class Mob {
    
    private World world;
    private Vector2F pos = new Vector2F();
    private String name;
    private int level;
    private int str;
    private double health = level*100 + str;
    private boolean isAlive;

    public Mob(Vector2F pos) {
        this.pos = pos;
    }

    public Mob() {
    }
 
    public void init(World world){
        this.world = world;
    }      
   
    public void tick(double deltaTime){
        
    }
    
    public void render(Graphics2D g){
        
        g.fillRect((int)pos.getWorldLocation().xpos*world.blockSize, (int)pos.getWorldLocation().ypos*world.blockSize, 40, 40);
    }
    
}
