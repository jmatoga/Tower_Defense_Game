package enemies;

import help.Constants;
import help.ImgFix;
import managers.EnemyManager;

import java.awt.*;

import static help.Constants.Direction.*;

public abstract class Enemy {
    protected EnemyManager enemyManager;
    protected float x, y;
    protected Rectangle bounds; //do hitboxów
    protected int health;
    protected int maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive = true;

    public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int)x, (int)y , 50, 50);
        lastDir = -1;
        setStartHealth();
    }

    private void setStartHealth(){
       health =  help.Constants.Enemies.GetStartHealth(enemyType);
       maxHealth = health;

    }

    public void hurt(int dmg){
        this.health -= dmg;
        if(health <= 0) {
            alive = false;
            health = 0;
            enemyManager.rewardPlayer(enemyType);
        }
    }

    public void kill() {
        // Is for killing enemy, when it reaches the end
        alive = false;
        health = 0;
    }

    public void move(float speed, int dir){
        lastDir = dir; //gdy zmienimy kierunek, zmienimy też ostatni kierunek
        switch(dir){
            case LEFT:
                this.x -= speed;
                //enemyManager.rotateEnemy( 90);
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
        updateHitbox();
    }

    private void updateHitbox() {
        bounds.x = (int) x;
        bounds.y = (int) y;
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

    public float getHealthBarFloat(){
        return health /(float) maxHealth;
    }

//    public void setX(float x) {
//        this.x = x;
//    }

//    public void setY(float y) {
//        this.y = y;
//    }

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

    public boolean isAlive(){
        return alive;
    }

}
