package scenes;

import enemies.TurboHardUnit;
import help.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.TileManager;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private TileManager tileManager;
    private ActionBar bottomBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;

    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        tileManager = new TileManager();
        bottomBar = new ActionBar(0, 750, 1200, 200, this);
        enemyManager = new EnemyManager(this);

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

    public void setLevel(int[][] lvl){
        this.lvl = lvl;
    }

    /**
     * Wywołanie zmiany stanu gry
     */
    public void update(){
        enemyManager.update();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.img_bg, 0, 0, null);
        drawLevel(g);
        bottomBar.draw(g);
        enemyManager.draw(g);
    }

    private void drawLevel(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id),x *50,y*50,null );
            }
        }
    }
    /**
     * Sprawdzenie id bloku, na podstawie tego, zwrócenie typu bloku
     * @param x Kolumna
     * @param y Wiersz
     */
    public int getTileType(int x, int y){
        int xCord = x/50;
        int yCord = y/50;

        if(xCord < 0 || xCord > 23)
            return 0;
        if(yCord < 0 || yCord > 14)
            return 0;

        int id = lvl[y/50][x/50];
        return getGame().getTileManager().getTile(id).getTileType();
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
