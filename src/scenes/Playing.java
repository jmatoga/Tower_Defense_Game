package scenes;

import help.LoadSave;
import main.Game;
import managers.TileManager;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private TileManager tileManager;
    private ActionBar bottomBar;
    private int mouseX, mouseY;

    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        tileManager = new TileManager();
        bottomBar = new ActionBar(0, 750, 1200, 200, this);

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

    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.img_bg, 0, 0, null);

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 50, y * 50, null);
            }
        }

        bottomBar.draw(g);
    }

    private BufferedImage getSprite(int spriteID){
        return getGame().getTileManager().getSprite(spriteID);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 750)
            bottomBar.mouseClicked(x, y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 750) {
            bottomBar.mouseMoved(x, y);
        } else {
            // żeby nie latało "smooth" tylko zeby sie "przyklejało" do kratki
            mouseX = (x / 50) * 50;
            mouseY = (y / 50) * 50;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 750)
            bottomBar.mousePressed(x, y);
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

}
