/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.main;

import java.awt.image.BufferedImage;
import my.project.gop.main.SpriteSheet;
import my.project.gop.main.loadImageFrom;

/**
 *
 * @author Andre
 * Classe responsável por realizar o carregamento dos sprites
 */
public class Assets {
    
    SpriteSheet blocks = new SpriteSheet();
    public static SpriteSheet player = new SpriteSheet();
    
    //floors
    public static BufferedImage stone_1;
    public static BufferedImage void_1;
    public static BufferedImage dirt_1;
    public static BufferedImage wood_1;
    public static BufferedImage wood_2_V;
    public static BufferedImage sand_1;
    public static BufferedImage grass_1;
    public static BufferedImage spawn_pos;
    //walls
    public static BufferedImage wall_1;
    public static BufferedImage wall_1_torch1;
    public static BufferedImage wall_1_torch2;
    public static BufferedImage wall_1_window;
    public static BufferedImage wall_1_brown;
    public static BufferedImage wall_1_wine;
    public static BufferedImage wall_1_roof;

    //mouse
    public static BufferedImage pressed;
    public static BufferedImage unpressed;
    
    //button
    public static BufferedImage button_heldover;
    public static BufferedImage button_notover;
    public static BufferedImage button_clicked;
        
    public void init(){
        blocks.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "spritesheet.png"));
        player.setSpriteSheet(loadImageFrom.LoadImageFrom(Main.class, "PlayerSheet.png"));
        
        //floors
        stone_1 = blocks.getTile(0, 0, 16, 16);
        dirt_1 = blocks.getTile(0, 16, 16, 16);
        wood_1 = blocks.getTile(0, 16*3, 16, 16);
        wood_2_V = blocks.getTile(0, 16*4, 16, 16);
        grass_1 = blocks.getTile(0, 16*5, 16, 16);
        sand_1 = blocks.getTile(0, 16*2, 16, 16);
        void_1 = blocks.getTile(16*2, 16, 16, 16);
        spawn_pos = blocks.getTile(16*4, 16, 16, 16);
        
        //walls
        wall_1 = blocks.getTile(16, 0, 16, 16);
        wall_1_torch1 = blocks.getTile(16*3, 0, 16, 16);
        wall_1_torch2 = blocks.getTile(16*4, 0, 16, 16);
        wall_1_window = blocks.getTile(16*2, 0, 16, 16);
        wall_1_brown = blocks.getTile(16, 16, 16, 16);
        wall_1_wine = blocks.getTile(16, 16*2, 16, 16);
        wall_1_roof = blocks.getTile(16*3, 16, 16, 16);
        
        //mouse
        unpressed = player.getTile(0, 0, 16, 16);
        pressed = player.getTile(16, 0, 16, 16);
        
        //buttons
        button_notover = player.getTile(16*2, 0, 16, 8);
        button_heldover = player.getTile(16*2, 8, 16, 8);
        button_clicked = player.getTile(16*3, 0, 16, 8);
        
        //head
   }

    public static BufferedImage getStone_1() {
        return stone_1;
    }
    
    public static BufferedImage getVoid_1() {
        return void_1;
    } 
    
    public static BufferedImage getWall_1() {
        return wall_1;
    }

    public static BufferedImage getWall_1_torch1() {
        return wall_1_torch1;
    }
    public static BufferedImage getWall_1_torch2() {
        return wall_1_torch2;
    }

    public static BufferedImage getDirt_1() {
        return dirt_1;
    }

    public static BufferedImage getWall_1_brown() {
        return wall_1_brown;
    }

    public static BufferedImage getWood_1() {
        return wood_1;
    }

    public static BufferedImage getWall_1_wine() {
        return wall_1_wine;
    }

    public static BufferedImage getWall_1_window() {
        return wall_1_window;
    }

    public static BufferedImage getSand_1() {
        return sand_1;
    } 

    public static BufferedImage getMousePressed() {
        return pressed;
    }

    public static BufferedImage getMouseUnpressed() {
        return unpressed;
    }

    public static BufferedImage getWall_1_roof() {
        return wall_1_roof;
    }

    public static BufferedImage getWood_2_V() {
        return wood_2_V;
    }

    public static BufferedImage getGrass_1() {
        return grass_1;
    }

    public static BufferedImage getButton_heldover() {
        return button_heldover;
    }

    public static BufferedImage getButton_notover() {
        return button_notover;
    }

    public static BufferedImage getButton_clicked() {
        return button_clicked;
    }

    public static BufferedImage getSpawn_pos() {
        return spawn_pos;
    }


    
    
    
}
