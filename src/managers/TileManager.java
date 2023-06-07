package managers;

import Objects.Tile;
import help.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    public Tile PATH, BLANK_PATH, START_PATH, END_PATH;
    public BufferedImage resource;
    public BufferedImage img_bg;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager()
    {
        loadBG();
        loadResource();
        createTiles();
    }

    private void createTiles() {

        tiles.add(PATH = new Tile(getSprite(0,0)));
        tiles.add(BLANK_PATH = new Tile(getSprite(2,0)));
        tiles.add(START_PATH = new Tile(getSprite(0,1)));
        tiles.add(END_PATH = new Tile(getSprite(1,1)));
    }

    private void loadResource() {
            resource = LoadSave.getSpriteResource();
    }

    private void loadBG() {
             img_bg = LoadSave.getImageBG();
    }


    public BufferedImage getSprite(int id)
    {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int x, int y)
    {
        return resource.getSubimage(x*50,y*50,50,50);
    }
}
