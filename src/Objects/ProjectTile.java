package Objects;

import java.awt.geom.Point2D;

public class ProjectTile {

    private Point2D.Float pos;
    private int id, projectTileType;
    private boolean active = true;

    public ProjectTile(float x, float y, int id, int projectTileType) {
        this.pos = new Point2D.Float(x,y);
        this.id = id;
        this.projectTileType = projectTileType;
    }

    public void move(float x, float y){
        pos.x += x;
        pos.y += y;
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

    public int getProjectTileType() {
        return projectTileType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
