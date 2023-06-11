package scenes;

import Objects.PathPoint;
import Objects.Tower;
import help.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.TileManager;
import managers.TowerManager;
import ui.ActionBar;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static help.Constants.Tiles.OTHER_TILE;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private TileManager tileManager;
    private ActionBar actionBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private PathPoint start,end;
    private Tower selectedTower;

    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        tileManager = new TileManager();
        actionBar = new ActionBar(0, 750, 1200, 200, this);
        enemyManager = new EnemyManager(this,start,end);
        towerManager = new TowerManager(this);

    }

    /*
     * Zapis aktualnego poziomu

    public void saveLevel(){
        LoadSave.SaveLevel("new_level",lvl);
    }*/

    /**
     * Wczytanie danego poziomu
     */
    private void loadDefaultLevel(){
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");

        start = points.get(0);
        end = points.get(1);
    }

    public void setLevel(int[][] lvl){
        this.lvl = lvl;
    }

    /**
     * Wywołanie zmiany stanu gry
     */
    public void update(){
        enemyManager.update();
        towerManager.update();
    }

    /**
     * Ustawienie klikniętej wieży jako wybranej
     * @param selectedTower Wybrana wieża
     */
    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.img_bg, 0, 0, null);
        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        drawSelectedTower(g);

    }

    /**
     * Rysowanie wybranej wieży
     * @param g Obiekt Graphics na którym rysujemy
     */
    private void drawSelectedTower(Graphics g) {
        if(selectedTower != null)
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()],mouseX,mouseY,null );
    }

    /**
     * Rysowanie poziomu na ekranie, na podstawie tablicy 2D lvl
     * @param g Obiekt Graphics na którym rysujemy
     */
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

    /**
     *
     * @param mouseX
     * @param mouseY
     * @return
     */
    private Tower getTowerAt(int mouseX, int mouseY) {
        return towerManager.getTowerAt(mouseX,mouseY);
    }

    /**
     * isTileGrass
     * @param x
     * @param y
     * @return True jeśli blok nie jest drogą
     */
    private boolean isNotRoad(int x, int y) {
        int id = lvl[y/50][x/50];
        int tileType = getGame().getTileManager().getTile(id).getTileType();
        System.out.println("TYPE: " + tileType);
        return tileType == OTHER_TILE;
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 750)
            actionBar.mouseClicked(x, y);
        else{
            if(selectedTower != null){
                if(isNotRoad(mouseX,mouseY)){
                    if(getTowerAt(mouseX,mouseY) == null){
                        towerManager.addTower(selectedTower, mouseX,mouseY);
                        selectedTower = null;
                    }
                }
            }else{ //
                Tower t = getTowerAt(mouseX,mouseY);
                if(t == null)
                    return;
                else{ //jeśli istnieje to wyświetlamy informacje o wieży
                    actionBar.displayTower(t);
                }
            }
        }
    }
    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 750) {
            actionBar.mouseMoved(x, y);
        } else {
            // żeby nie latało "smooth" tylko zeby sie "przyklejało" do kratki
            mouseX = (x / 50) * 50;
            mouseY = (y / 50) * 50;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 750)
            actionBar.mousePressed(x, y);
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
    public TowerManager getTowerManager(){
        return towerManager;
    }



}
