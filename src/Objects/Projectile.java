package Objects;

import java.awt.geom.Point2D;

public class Projectile {

    private Point2D.Float pos;
    private int id, projectileType, dmg;
    private float xSpeed,ySpeed;
    private boolean active = true;

    public Projectile(float x, float y, float xSpeed,float ySpeed, int dmg, int id, int projectTileType) {
        pos = new Point2D.Float(x,y);
        this.id = id;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        this.projectileType = projectTileType;
    }

    public void reuse(int x, int y, float xSpeed, float ySpeed, int dmg) {
        pos = new Point2D.Float(x -25,y-25);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.dmg = dmg;
        active = true;
    }

    public void move(){
        pos.x += xSpeed;
        pos.y += ySpeed;
    }

    public Point2D.Float getPos() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public int getProjectileType() {
        return projectileType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDmg() {
        return dmg;
    }
}
