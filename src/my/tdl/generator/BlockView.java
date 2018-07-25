package my.tdl.generator;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import my.project.gop.main.Vector2F;

/**
 *
 * @author Andre
 */
public class BlockView extends Rectangle{
    
    /**
     * This is the world position of the block.
     * @pos
     */
    private Vector2F pos;
    /**
     * This is the block sprite itself.
     * @block_image
     */
    private BufferedImage block_image;    
    private double rotation;
    private double rotation_speed = 0.8;
    private double blockSize = 24;
    private boolean isAlive;
    private int lifeTime;
    private boolean isDying;
    private float lifeFade = 1.0f;
    /**
     * This is the world map wanted to render the view.
     * @world
     */
    private World world;
            
    public BlockView(Vector2F pos, BufferedImage block_image) {
        this.block_image = block_image;
        this.pos = pos;
        
        rotation = new Random().nextInt(180);
        lifeTime = new Random().nextInt(500);
        if(lifeTime < 300){
            lifeTime = new Random().nextInt(500);
        }
        setBounds((int)pos.xpos, (int)pos.ypos, (int)blockSize, (int)blockSize);
        isAlive = true;
    }
    /**
     * This function is responsible for the block refreshing.
     * This means that at each delta time passed the sprites will be 
     * refreshed.
     * @param deltaTime sets the time to refresh.
     */
    public void tick(double deltaTime){
        if(isAlive){
            rotation -= rotation_speed;
            setBounds((int)pos.xpos, (int)pos.ypos, (int)blockSize, (int)blockSize);
            
            if(!isDying){
                if (lifeTime != 0) {
                    lifeTime--;
                }
                if (lifeTime == 0) {
                    isDying = true;
                }
            }
            if(isDying){
                if(lifeFade > 0.0001){
                    lifeFade -= 0.01;
                }
                if(lifeFade <= 0.8 && lifeFade >= 0.7){
                    blockSize+= 0.3;
                }
                if(lifeFade < 0.7){
                    blockSize -= 0.3;
                    pos.xpos += 0.1;
                    pos.ypos += 0.1;
                }
                if(lifeFade <= 0.1){
                    world.removeDropedEntity(this);
                    isAlive = false;
                }
            }
        }
    }
    /**
     * This function is responsible for rendering the blocks sprites on the map.
     * @param g 
     */
    public void render(Graphics2D g){
        if(isAlive){
            if(isDying){
                
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, lifeFade)); //Cria um fade out a partir do lifefade
            }
        g.rotate(Math.toRadians(rotation), 
                pos.getWorldLocation().xpos + blockSize / 2, 
                pos.getWorldLocation().ypos + blockSize / 2
                );
        ///////////O QUE ESTIVER AQUI SERÁ ROTACIONADO E TERA EFEITO FADE OUT COM LIFETIME

        g.drawImage(block_image,
                (int) pos.getWorldLocation().xpos,
                (int) pos.getWorldLocation().ypos,
                (int) blockSize,
                (int) blockSize, null
                    );
        g.drawRect(
                (int) pos.getWorldLocation().xpos,
                (int) pos.getWorldLocation().ypos,
                (int) blockSize,
                (int) blockSize);
        
        /////////// O código abaixo retorna os objetos renderizados para sua posição original, senão todo o mapa ficará girando.
        g.rotate(-Math.toRadians(rotation), 
                pos.getWorldLocation().xpos + blockSize / 2, 
                pos.getWorldLocation().ypos + blockSize/2
                );
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
    }
    /**
     * This function is responsible for setting the aliveness of the block.
     * This means that the block is rendered.
     * @param isAlive 
     */
    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
}
