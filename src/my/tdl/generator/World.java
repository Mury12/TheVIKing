/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.tdl.generator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;
import my.project.gop.main.FPS;
import my.project.gop.main.Vector2F;
import my.project.gop.main.loadImageFrom;
import my.tdl.MoveableObjects.Hostile;
import my.tdl.MoveableObjects.Player;
import my.tdl.gamestate.GameStateManager;
import my.tdl.gamestates.DungeonLevelLoader;
import my.tdl.main.Main;

/**
 *
 * @author Andre
 */
public class World {

    private GameStateManager gsm;
    public Vector2F map_pos = new Vector2F();
    public TileManager tiles;
    private static Player player;
    private String worldName;
    private BufferedImage map;
    private int world_width;
    private int world_height;
    public static CopyOnWriteArrayList<BlockEntity> blockents;
    public static int blockSize = Block.BlockSize;    
    public boolean hasSize = false;
    private FPS fps = new FPS();
    public Vector2F spawnPos;
    public boolean hasGenerated;
    private Hostile mob;
    //WorldSpawn
    private Block spawn;
    
    public World(String worldName, BufferedImage world_image, int w_width, int w_height) {
        this.worldName = worldName;
        this.map = world_image;
        this.world_width = w_width;
        this.world_height = w_height;

    }
    public World(String world_name){
        this.worldName = world_name;
        Vector2F.setWorldVariables(map_pos.xpos, map_pos.ypos);
    }

    public World(String worldName, GameStateManager gsm) {
        this.worldName = worldName;
        this.gsm = gsm;
    }
    
    public void init(){
        blockents = new CopyOnWriteArrayList<>();
        tiles = new TileManager(this);
        mob = new Hostile(new Vector2F(2, 2));
        map_pos.xpos = spawn.getBlockLocation().xpos - player.getPos().xpos + 10;
        map_pos.ypos = spawn.getBlockLocation().ypos - player.getPos().ypos + 8;
        
        fps.start();
        if(player != null)
            player.init(this);
    }
        
    public void generateWorld(String world_image) {
        
        
        map = null;
        if (hasSize) {
            try {
                map = loadImageFrom.LoadImageFrom(Main.class, world_image);
            } catch (Exception e) {

            }

            //loop de todos os pixels da imagem do mapa
            for (int x = 0; x < world_width; x++) {
                for (int y = 0; y < world_height; y++) {

                    int col = map.getRGB(x, y); //pega a cor de cada pixel
                    //transforma os pixels da cor XXXXX no bloco que desejar
                    switch (col & 0xFFFFFF) { //0xFFFFFF é o código da cor utilizada para preencher com os tiles stone_1
                        //FLOORS
                        case 0x808080: //caso a cor seja...
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.STONE_1)); // *32 faz com que cada bloco seja exibido em 32x32 px
                            break;
                        case 0xFFFEB8:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.SAND_1));
                            break;
                        case 0x828126:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.DIRT_1));
                            break;
                        case 0xd5c768:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.WOOD_1));
                            break;
                        case 0xaa9f54:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.WOOD_2_V));
                            break;
                        case 0x69c33c:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.GRASS_1));
                            break;

                        //WALLS
                        case 0x404040: //caso a cor seja...
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.WALL_1).isSolid(true)); // *32 faz com que cada bloco seja exibido em 32x32 px
                            break;
                        case 0xFFFC00:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.WALL_1_TORCH).isSolid(true));
                            break;
                        case 0xB8B8B8:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.WALL_1_ROOF).isSolid(true));
                            break;
                        case 0xAEB459:
                            tiles.blocks.add(new Block(new Vector2F(x * blockSize, y * blockSize), Block.BlockType.SPAWN_POS));
                            break;

                    }
                }
            }
        }
        hasGenerated = true;
    }

    public void addPlayer(Player player){
        player = new Player();
        this.player = player;
    }
    
    public void tick(double deltaTime) {
        if(player.hasSpawned()){
            Vector2F.setWorldVariables(map_pos.xpos, map_pos.ypos);
        }else{
            spawn.tick(deltaTime);
        }
        tiles.tick(deltaTime);
        
        if (!blockents.isEmpty()) {
            for (BlockEntity ent : blockents) {
                if (player.render.intersects(ent)) {
                    ent.tick(deltaTime);
                    ent.setAlive(true);
                } else {
                    ent.setAlive(false);
                }
            }
        }
        if(player != null)
        player.tick(deltaTime);
    }
    
    public static void dropBlockEntity(Vector2F pos, BufferedImage block_image){
        BlockEntity ent = new BlockEntity(pos, block_image);
        if(!blockents.contains(ent)){
            blockents.add(ent);
        }
    }
    
    public void setWorldSpawn(float xPos, float yPos){
        if(xPos < world_width || yPos < world_height)
        if(xPos < world_width){
            if(yPos < world_height){
                Block setspw = new Block(new Vector2F(xPos*blockSize, yPos*blockSize));
                this.spawn = setspw;
            }else{
                System.out.println("Y spawn position out of the map!");
            }
        }else{
                System.out.println("X spawn position out of the map!");
            }
        else System.out.println("X AND Y spanw position out of the ");
    }
    
   public Vector2F getWorldSpawn(){
       return spawn.pos;
   }
    
    public static void removeDropedEntity(BlockEntity blockEntity) {
        if(blockents.contains(blockEntity)){
            blockents.remove(blockEntity);
        }
    }
    
    //renderiza o mapa a partir do TileManager
    public void render(Graphics2D g) {
        tiles.render(g);
        if(!player.hasSpawned()){
            spawn.render(g);
        }
        ///descarrega os blocos que não estão na tela
        if(!blockents.isEmpty()){ //só renderiza se não estiver vazio, diminuindo o lag
            for(BlockEntity ent : blockents){
                if(player.render.intersects(ent))
                    ent.render(g);
            }
        }
        if(player != null)
            player.render(g);
        if(mob != null)
            mob.render(g);
        
        if(Player.isDebugging()){
            String str = "BlockEnt";
            g.drawString("BlockEnt:  "+blockents.size(), Main.width - (str.length()+5)*8, 10);
            g.drawString("FPS:  "+(int)fps.getFps(), Main.width - (str.length()+5)*8, 23);
        }
    }

    public String getWorldName() {
        return worldName;
    }

    public int getWorld_height() {
        return world_height;
    }

    public BufferedImage getWorld_image() {
        return map;
    }

    public int getWorld_width() {
        return world_width;
    }

    public void setWorld_height(int world_height) {
        this.world_height = world_height;
    }

    public void setWorld_width(int world_width) {
        this.world_width = world_width;
    }
    
    public void setWorldSize(int width, int height){
        setWorld_width(width);
        setWorld_height(height);
        hasSize = true;
    }
    
    public Vector2F getWorldPos(){
        return map_pos;
    }

    public Vector2F getSpawnPos() {
        return spawnPos;
    }

    public TileManager getTiles() {
        return tiles;
    }

    public static Player getPlayer() {
        return player;
    }
    
    public boolean hasGenerated(){
        return this.hasGenerated;
    }
    
    public GameStateManager getGsm(){
        return gsm;
    }
    
    public void changeLevel(String worldname, String map_name){
        if(!worldname.equals(this.worldName)){
            resetWorld();
            gsm.states.push(new DungeonLevelLoader(gsm, worldname, map_name));
            gsm.states.peek().init();
        }else{
            System.err.println("You are alrd in that wld");
        }
    }
    
    public void resetWorld(){
        tiles.getBlocks().clear();
        tiles.getLoaded_blocks().clear();
        blockents.clear();
        spawn = null;
    }
}
