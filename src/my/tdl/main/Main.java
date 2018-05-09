/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.main;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import my.project.gop.main.GameWindow;
import my.tdl.MoveableObjects.Player;
import my.tdl.gameloop.GameLoop;
import my.tdl.managers.Mousemanager;

/**
 *
 * @author Andre
 */
public class Main {

    public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public static int width = gd.getDisplayMode().getWidth(); //captura o tamanho da tela
    public static int height = gd.getDisplayMode().getHeight();
    
    public static void main(String[] args) {
        GameWindow frame = new GameWindow("The VI King", width, height); //Cria uma janela baseada na classe do projeto GameOperatingSystem, definia anteriormente e exportada como jar.
        frame.setFullScreen(1);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor cursor = toolkit.createCustomCursor(toolkit.getImage(""), new Point(0,0), "Cursor");
        frame.setCursor(cursor);
        
        frame.addMouseListener(new Mousemanager());
        frame.addMouseMotionListener(new Mousemanager());
        frame.addMouseWheelListener(new Mousemanager());
        
        frame.addKeyListener(new Player());
        frame.add(new GameLoop(width, height));
        frame.setVisible(true);
        /*blocks.setSpriteSheet("../img/spritesheet.png");
        **blocks.getTile(0, 0, 8, 8);
         */
    }
}
