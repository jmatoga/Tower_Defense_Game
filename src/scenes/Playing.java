package scenes;

import Objects.Tile;
import help.LevelBuild;
import help.LoadSave;
import main.Game;
import managers.TileManager;
import ui.BottomBar;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private TileManager tileManager;
    private Tile selectedTile;
    private BottomBar bottomBar;
    private int mouseX, mouseY;
    private int lastTileX,lastTileY,lastTileId;
    private boolean drawSelect = false;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 750, 1200, 200, this);
        //LoadSave.CreateFile();
        //LoadSave.WriteToFile();
        //LoadSave.ReadFromFile();

        createDefaultLevel();
        loadDefaultLevel();
    }

    /**
     * Zapis aktualnego poziomu
     */
    public void saveLevel(){
        LoadSave.SaveLevel("new_level",lvl);
    }

    /**
     * Wczytanie danego poziomu
     */
    private void loadDefaultLevel(){
        lvl = LoadSave.GetLevelData("new_level");
    }

    /**
     * Tworzenie nowego poziomu z wartościami domyślnymi
     */
    private void createDefaultLevel(){
        int[] arr = new int[360];
        for(int i = 0; i < arr.length; i++){
            arr[i] = 0; //pierwsza tekstura
        }
        LoadSave.CreateLevel("new_Level",arr);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.img_bg, 0, 0, null);

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 50, y * 50, null);
            }
        }

        bottomBar.draw(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 50, 50, null);
        }
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 750)
            bottomBar.mouseClicked(x, y);
        else {
            changeTile(mouseX,mouseY);
        }
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
    public void mouseMoved(int x, int y) {
        if (y >= 750) {
            bottomBar.mouseMoved(x, y);
            drawSelect = false;
        } else {
            drawSelect = true;
            // żeby nie latało "smooth" tylko zeby sie "przyklejało" do kratki
            mouseX = (x / 50) * 50;
            mouseY = (y / 50) * 50;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 750)
            bottomBar.mousePressed(x, y);
        else {
            changeTile(mouseX,mouseY);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
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
