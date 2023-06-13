package managers;

import Objects.Tower;
import enemies.Enemy;
import help.LoadSave;
import scenes.Playing;
import help.Constants.Towers.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static help.Constants.Towers.BASIC;

public class TowerManager {
    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;
        loadTowerImgs();
    }

    /**
     * Ładowanie wież do tablicy
     */
    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteResource();
        towerImgs = new BufferedImage[9];
        for (int i = 0; i < 8; i++)
            towerImgs[i] = atlas.getSubimage((i + 2) * 50, 1 * 50, 50, 50);

        towerImgs[8] = atlas.getSubimage(0 * 50, 2 * 50, 50, 50); // dodanie wiezy z dolu 3 rzędu
    }

    public void update() {
        for (Tower t : towers) {
            t.update();
            attackEnemyIfClose(t);
        }
    }

    private void attackEnemyIfClose(Tower t) {
        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                if (isEnemyInRange(t, e)) {
                    if (t.isCooldownOver()) {
                        playing.shootEnemy(t, e);
                        t.resetCooldown();
                    }
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = help.Utils.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());

        return range < t.getRange();
    }

    public void addTower(Tower selectedTower, int xPos, int yPos) {
        towers.add(new Tower(xPos, yPos, towerAmount++, selectedTower.getTowerType()));
    }

    public void removeTower(Tower displayedTower) {
        for (int i = 0; i < towers.size(); i++) {
            if(towers.get(i).getId() == displayedTower.getId())
                towers.remove(i);
        }
    }

    public void upgradeTower(Tower displayedTower) {
        for(Tower t : towers) {
            if(t.getId() == displayedTower.getId())
                t.upgradeTower();
        }
    }

    /**
     * Wywołanie rysowania wież
     *
     * @param g Obiekt na którym rysujemy
     */
    public void draw(Graphics g) {
        for (Tower t : towers) {
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
        }
    }

    /**
     * Sprawdzenie czy na danej pozycji jest już obiekt (zwrócenie go)
     *
     * @param x Kolumna
     * @param y Wiersz
     * @return Zwraca obiekt na danej pozycji lub null
     */
    public Tower getTowerAt(int x, int y) {
        for (Tower t : towers)
            if (t.getX() == x)
                if (t.getY() == y)
                    return t;
        return null;
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public void reset(){
        towers.clear();
        towerAmount = 0;
    }
}
