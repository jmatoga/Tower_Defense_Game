package managers;

import enemies.Enemy;
import enemies.*;
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
    private float speed = 0.5f;
    public EnemyManager(Playing playing) {
        this.playing = playing;
        enemyImgs = new BufferedImage[7];
        addEnemy(50*0,50*5, TUTORIAL_UNIT);
        addEnemy(50*6,50*7, EASY_UNIT);
        addEnemy(50*8,50*7, NORMAL_UNIT);
        addEnemy(50*11,50*12, HARD_UNIT);
        addEnemy(50*13,50*12, SUPER_UNIT);
        addEnemy(50*15,50*10, TURBO_HARD_UNIT);
        addEnemy(50*20,50*2, OWN_UNIT);
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
        for(Enemy e : enemies)
            updateEnemyMove(e);
    }

    /**
     * Zmienia pozycję moba
     * @param e Mob, który wykonuje ruch
     */
    private void updateEnemyMove(Enemy e) {
        if(e.getLastDir() == -1)
            setNewDirectionAndMove(e);

        int newX = (int)(e.getX() + getSpeedAndWidth(e.getLastDir()));
        int newY = (int)(e.getY() + getSpeedAndHeight(e.getLastDir()));

        if(getTileType(newX,newY) == ROAD_TILE){
            //idziemy dalej w tym samym kierunku
            e.move(speed, e.getLastDir());
        }else if(isAtEnd(e)){
            //
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

        if(dir == LEFT || dir == RIGHT) {
            int newY = (int) (e.getY() + getSpeedAndHeight(UP));
            if (getTileType((int) e.getX(), newY) == ROAD_TILE)
                e.move(speed, UP);
            else
                e.move(speed, DOWN);
        }else{
            int newX = (int)(e.getX() + getSpeedAndWidth(RIGHT));
            if (getTileType(newX, (int) e.getY()) == ROAD_TILE)
                e.move(speed,RIGHT);
            else e.move(speed,LEFT);
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

    private boolean isAtEnd(Enemy e) {
        return false;
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x,y);
    }

    private float getSpeedAndHeight(int dir) {
        if(dir == UP)
            return -speed;
        else if(dir == DOWN)
            return speed + 50;
        return 0;
    }

    private float getSpeedAndWidth(int dir) {
        if(dir == LEFT)
            return -speed;
        else if(dir == RIGHT)
            return speed + 50;
        return 0;
    }

    public void addEnemy(int x, int y, int enemyType){
        switch (enemyType){
            case TUTORIAL_UNIT:
                enemies.add(new TutorialUnit(x,y,0));
                break;
            case EASY_UNIT:
                enemies.add(new EasyUnit(x,y,0));
                break;
            case NORMAL_UNIT:
                enemies.add(new NormalUnit(x,y,0));
                break;
            case HARD_UNIT:
                enemies.add(new HardUnit(x,y,0));
                break;
            case SUPER_UNIT:
                enemies.add(new SuperUnit(x,y,0));
                break;
            case TURBO_HARD_UNIT:
                enemies.add(new TurboHardUnit(x,y,0));
                break;
            case OWN_UNIT:
                enemies.add(new OwnUnit(x,y,0));
                break;
        }
        //enemies.add(new TutorialUnit(x,y,0));
    }

    /**
     * Rysowanie obiektu
     * @param g Obiekt na którym "rysujemy"
     */
    public void draw(Graphics g){
        for(Enemy e : enemies)
            drawEnemy(e,g);
    }

    /**
     * Rysowanie moba
     * @param e Mob którego rysujemy
     * @param g Obiekt na którym "rysujemy"
     */
    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int) e.getX(), (int) e.getY(), null);
    }
}
