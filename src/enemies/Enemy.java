package enemies;

import help.Constants;

import java.awt.*;

import static help.Constants.Direction.*;

public abstract class Enemy {
    private float x, y;
    private Rectangle bounds; //do hitboxów
    private int health;
    private int ID;
    private int enemyType;
    private int lastDir;

    public Enemy(float x, float y, int ID, int enemyType){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x, (int)y, 50, 50);
        lastDir = -1;
    }

    protected void setStartHealth(){
       health =  help.Constants.Enemies.GetStartHealth(enemyType);

    }

    public void move(float speed, int dir){
        lastDir = dir; //gdy zmienimy kierunek, zmienimy też ostatni kierunek
        switch(dir){
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }
    }

    /**
     * Funkcja do naprawy pozycji podczas ruchu, NIE UŻYWAĆ DO RUCHU (move)
     * @param x Kolumna
     * @param y Wiersz
     */
    public void setPos(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }
    public int getLastDir() {
        return lastDir;
    }

}
