package managers;

import Objects.Tile;
import help.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile PATH, BLANK_PATH, START_PATH, END_PATH, TEST;
    public BufferedImage resource;
    public BufferedImage img_bg;
    public ArrayList<Tile> tiles = new ArrayList<>();

    //public Tile blank_path_visible = new Tile(getSprite(7, 2), 100, "Blank_Path_Visible");

    public TileManager() {
        loadBG();
        loadResource();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(PATH = new Tile(getSprite(0, 0), id++, "Path"));
        tiles.add(BLANK_PATH = new Tile(getSprite(2, 0), id++, "Blank_Path"));
        tiles.add(START_PATH = new Tile(getSprite(0, 1), id++, "Start_Path"));
        tiles.add(END_PATH = new Tile(getSprite(1, 1), id++, "End_Path"));

    }

    public Tile getTile(int id){
        return tiles.get(id);
    }


    private void loadResource() {
        resource = LoadSave.getSpriteResource();
    }

    private void loadBG() {
        img_bg = LoadSave.getImageBG();
    }

    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int x, int y) {
        return resource.getSubimage(x * 50, y * 50, 50, 50);
    }

    public BufferedImage getSpriteBlankVisible() {
        return resource.getSubimage(7 * 50, 2 * 50, 50, 50);
    }
}
