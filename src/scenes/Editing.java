package scenes;

import Objects.Tile;
import help.LoadSave;
import main.Game;
import managers.TileManager;
import ui.ToolBar;
import java.awt.*;

public class Editing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private TileManager tileManager;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX,lastTileY,lastTileId;
    private boolean drawSelect;
    private ToolBar toolBar;

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
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.img_bg, 0, 0, null);

        toolBar.draw(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 50, 50, null);
        }
    }
    public void saveLevel(){
        LoadSave.SaveLevel("new_level",lvl);
    }
    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile(int x, int y) {
        if(selectedTile != null) {
            int tileX = x / 50;
            int tileY = y / 50;

            if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                return;

            lastTileX = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();

            lvl[tileY][tileX] = selectedTile.getId();
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
}
