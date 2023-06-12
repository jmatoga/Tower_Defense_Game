package managers;

import Objects.PathPoint;
import Objects.Tower;
import enemies.Enemy;
import enemies.*;
import help.ImgFix;
import help.LoadSave;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static help.Constants.Direction.*;
import static help.Constants.Enemies.*;
import static help.Constants.Tiles.*;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs; //tablica wrogów
    private ArrayList<Enemy> enemies = new ArrayList<>();
    //private float speed = 0.5f;
    private PathPoint start,end;

    private int HPbarWidth = 40;

    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        enemyImgs = new BufferedImage[7];
        this.start = start;
        this.end = end;

//        addEnemy(EASY_UNIT);
//        addEnemy(NORMAL_UNIT);
//        addEnemy(HARD_UNIT);
//        addEnemy(SUPER_UNIT);
//        addEnemy(TURBO_HARD_UNIT);
//        addEnemy(TUTORIAL_UNIT);
//        addEnemy(OWN_UNIT);

        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage sprities = LoadSave.getSpriteResource();
        for(int i = 0; i < 7; i++)
            enemyImgs[i] = sprities.getSubimage((3+i) * 50,0,50, 50);
    }

    /**
     * Aktualizacja moba
     */
    public void update(){
        updateWaveManager();

        if(isTimeForNewEnemy()) {
            spawnEnemy();
        }

        for(Enemy e : enemies)
            if(e.isAlive()) {
                updateEnemyMove(e);
            }
    }

    private void updateWaveManager() {
        playing.getWaveManager().update();
    }

    private void spawnEnemy() {
        addEnemy(playing.getWaveManager().getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(playing.getWaveManager().isTimeForNewEnemy())
            if(playing.getWaveManager().isThereMoreEnemiesInWave())
                return true;

        return false;
    }

    /**
     * Zmienia pozycję moba
     * @param e Mob, który wykonuje ruch
     */
    private void updateEnemyMove(Enemy e) {
        if(e.getLastDir() == -1)
            setNewDirectionAndMove(e);

        int newX = (int)(e.getX() + getSpeedAndWidth(e.getLastDir(),e.getEnemyType()));
        int newY = (int)(e.getY() + getSpeedAndHeight(e.getLastDir(),e.getEnemyType()));

        if(isAtEnd(e)){
            System.out.println("END!");
        }else if(getTileType(newX,newY) == ROAD_TILE){
            //idziemy dalej w tym samym kierunku
            e.move(getSpeed(e.getEnemyType()), e.getLastDir());
        }else{
            setNewDirectionAndMove(e);
        }
    }

    /**
     * Wybiera nowy kierunek ruchu moba i wykonuje ten ruch
     * @param e Mob, który wykonuje ruch
     */
    private void setNewDirectionAndMove(Enemy e) {
       int dir = e.getLastDir();

        int xCord = (int)e.getX() / 50;
        int yCord = (int)e.getY() / 50;

        fixEnemyOffsetTile(e,dir,xCord,yCord);

        if(isAtEnd(e))
            return;

        if(dir == LEFT || dir == RIGHT) {
            int newY = (int) (e.getY() + getSpeedAndHeight(UP,e.getEnemyType()));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE)
                e.move(getSpeed(e.getEnemyType()), UP);
            else
                e.move(getSpeed(e.getEnemyType()), DOWN);
        }else{
            int newX = (int)(e.getX() + getSpeedAndWidth(RIGHT,e.getEnemyType()));
            if (getTileType(newX, (int) e.getY()) == ROAD_TILE)
                e.move(getSpeed(e.getEnemyType()),RIGHT);
            else e.move(getSpeed(e.getEnemyType()),LEFT);
        }


    }

    /**
     * Wybiera nowy kierunek ruchu moba i wykonuje ten ruch
     * @param e Mob, który wykonuje ruch
     */
    private void fixEnemyOffsetTile(Enemy e, int dir, int xCord, int yCord) {
        switch(dir){
            case RIGHT:
                if(xCord < 23) //żeby nie przekroczyć rozmiaru mapy (by nie wyjść poza)
                    xCord++;
                break;
            case DOWN:
                if(yCord < 14)
                    yCord++;
                break;
        }
        e.setPos(xCord*50,yCord*50);
    }

    /**
     * Sprawdza czy na końcu (bloku końcowym)
     * @param e Mob, który wykonuje ruch
     * @return True jeśli na końcu
     */
    private boolean isAtEnd(Enemy e) {
        if(e.getX() == end.getxCord()*50)
            if(e.getY() == end.getyCord()*50)
                return true;
        return false;
    }

    /**
     * Zwraca numer typu bloku na podnaych współrzędnych
     * @param x Kolumna
     * @param y Wiersz
     * @return Typ bloku (int)
     */
    private int getTileType(int x, int y) {
        return playing.getTileType(x,y);
    }

    /**
     * W zależności czy mob idzie do góry czy w dół, zwraca zmodyfikowaną współrzędną X-ową
     * @param dir Kierunek w którym wykonuje ruch
     * @param enemyType Typ moba który wykonuje ruch (int)
     */
    private float getSpeedAndHeight(int dir, int enemyType) {
        if(dir == UP)
            return -getSpeed(enemyType);
        else if(dir == DOWN)
            return getSpeed(enemyType) + 50;
        return 0;
    }

    /**
     * W zależności czy mob idzie w prawo czy w lewo, zwraca zmodyfikowaną współrzędną Y-ową
     * @param dir Kierunek w którym wykonuje ruch
     * @param enemyType Typ moba który wykonuje ruch (int)
     */
    private float getSpeedAndWidth(int dir, int enemyType) {
        if(dir == LEFT)
            return -getSpeed(enemyType);
        else if(dir == RIGHT)
            return getSpeed(enemyType) + 50;
        return 0;
    }

    /**
     * Dodanie moba do tablicy enemies
     * @param enemyType Typ moba który jest dodawany (int)
     */
    public void addEnemy(int enemyType){
        int x = start.getxCord() * 50;
        int y = start.getyCord() * 50;

        switch (enemyType){
            case TUTORIAL_UNIT:
                enemies.add(new TutorialUnit(x,y,0,this));
                break;
            case EASY_UNIT:
                enemies.add(new EasyUnit(x,y,0,this));
                break;
            case NORMAL_UNIT:
                enemies.add(new NormalUnit(x,y,0,this));
                break;
            case HARD_UNIT:
                enemies.add(new HardUnit(x,y,0,this));
                break;
            case SUPER_UNIT:
                enemies.add(new SuperUnit(x,y,0,this));
                break;
            case TURBO_HARD_UNIT:
                enemies.add(new TurboHardUnit(x,y,0,this));
                break;
            case OWN_UNIT:
                enemies.add(new OwnUnit(x,y,0,this));
                break;
        }
        //enemies.add(new TutorialUnit(x,y,0,this));
    }

    /**
     * Rysowanie obiektu
     * @param g Obiekt na którym "rysujemy"
     */
    public void draw(Graphics g){
        for(Enemy e : enemies) {
            if(e.isAlive()) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
            }
        }

    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)e.getX() + 25 - (HPbarWidth/2), (int)e.getY() - 8, getNewBarWidth(e), 4);
    }

    private int getNewBarWidth(Enemy e){
        return (int)(HPbarWidth  * e.getHealthBarFloat());
    }

    /**
     * Rysowanie moba
     * @param e Mob którego rysujemy
     * @param g Obiekt na którym "rysujemy"
     */
    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
    }

    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }

    public void rotateEnemy(Enemy e, int routeAngle){
        ImgFix.getRotImg(enemyImgs[e.getEnemyType()], routeAngle);
    }

    public void rewardPlayer(int enemyType) {
        playing.rewardPlayer(enemyType);
    }
}
