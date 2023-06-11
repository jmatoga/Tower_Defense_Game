package managers;

import Objects.Tile;
import help.ImgFix;
import help.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static help.Constants.Direction.*;
import static help.Constants.Tiles.*;

public class TileManager {
    public Tile PATH, BLANK_PATH, START_PATH, END_PATH, ROTATED_PATH, CORNER_PATH1, CORNER_PATH2, CORNER_PATH3, CORNER_PATH4;
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
        tiles.add(PATH = new Tile(getSprite(0, 0), id++, ROAD_TILE));
        tiles.add(BLANK_PATH = new Tile(getSprite(2, 0), id++, OTHER_TILE));
        tiles.add(ROTATED_PATH = new Tile(ImgFix.getBuildRotImg(getImgs(2,0,0,0),45,0),id++,ROAD_TILE));
        tiles.add(START_PATH = new Tile(getSprite(0, 1), id++, ROAD_TILE));
        tiles.add(END_PATH = new Tile(getSprite(1, 1), id++, ROAD_TILE));
        tiles.add(CORNER_PATH1 = new Tile(getSprite(1, 0), id++, ROAD_TILE));
        tiles.add(CORNER_PATH2 = new Tile(ImgFix.getBuildRotImg(getImgs(2,0,1,0),45,0),id++,ROAD_TILE));
        tiles.add(CORNER_PATH3 = new Tile(ImgFix.getBuildRotImg(getImgs(2,0,1,0),90,0),id++,ROAD_TILE));
        tiles.add(CORNER_PATH4 = new Tile(ImgFix.getBuildRotImg(getImgs(2,0,1,0),135,0),id++,ROAD_TILE));

    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY){

        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secondX,secondY)};
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
