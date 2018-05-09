/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.MoveableObjects;

import java.awt.Graphics2D;
import my.project.gop.main.Vector2F;

/**
 *
 * @author Andre
 */
public class Hostile extends Mob{
    
    boolean attack;
    boolean targeting;

    public Hostile(Vector2F pos) {
        super(pos);
    }

    public void render(Graphics2D g){
        super.render(g);
    }
    
}
