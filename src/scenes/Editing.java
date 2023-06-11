package scenes;

import Objects.PathPoint;
import Objects.Tile;
import help.LoadSave;
import main.Game;
import ui.ToolBar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import managers.TileManager;

import static help.Constants.Tiles.*;

public class Editing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX,lastTileY,lastTileId;
    private boolean drawSelect;
    private ToolBar toolBar;
    private TileManager tileManager;
    private BufferedImage img_bg;
    private PathPoint start,end;

    public Editing(Game game) {
        super(game);
        loadDefaultLevel();
        tileManager = new TileManager();
        toolBar = new ToolBar(0, 750, 1200, 200, this);
    }

    /**
     * Wczytanie danego poziomu
     */
    private void loadDefaultLevel(){
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    @Override
    public void render(Graphics g) {
        importBG();
        g.drawImage(tileManager.img_bg, 0, 0, null);
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
        drawPathPoints(g);
    }

    private void drawPathPoints(Graphics g) {
        if(start != null){
            g.drawImage(toolBar.getStartPathImage(), start.getxCord()*50,
                    start.getyCord()*50,50,50,null);
        }
        if(end != null){
            g.drawImage(toolBar.getEndPathImage(), end.getxCord()*50,
                    end.getyCord()*50,50,50,null);
        }
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id),x *50,y*50,null );
            }
        }
    }
    private BufferedImage getSprite(int spriteID){
        return getGame().getTileManager().getSprite(spriteID);
    }
    private void importBG() {

        img_bg = null;
        InputStream is = getClass().getResourceAsStream("/res/tlo_2.png");

        try {
            img_bg = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 50, 50, null);
        }
    }

    public void saveLevel(){
        LoadSave.SaveLevel("new_level",lvl,start,end);
        getGame().getPlaying().setLevel(lvl);
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / 50;
            int tileY = y / 50;
            if (selectedTile.getId() != 3 && selectedTile.getId() != 4) { //początek lub koniec ścieżki
                if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                    return;

                lastTileX = tileX;
                lastTileY = tileY;
                lastTileId = selectedTile.getId();

                lvl[tileY][tileX] = selectedTile.getId();
            } else {
                int id = lvl[tileY][tileX];
                if(getGame().getTileManager().getTile(id).getTileType() == ROAD_TILE){
                    if(selectedTile.getId() == 3)
                        start = new PathPoint(tileX, tileY);
                    else
                        end =new PathPoint(tileX, tileY);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 750) {
            toolBar.mouseClicked(x, y);
        }else {
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 750) {
            toolBar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
            mouseX = (x / 50) * 50;
            mouseY = (y / 50) * 50;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 750)
            toolBar.mousePressed(x, y);
        else {
            changeTile(mouseX,mouseY);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        toolBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(y >= 750) {

        }
        else {
            changeTile(x,y);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        //if(e.getKeyCode() == KeyEvent.)
    }

}
