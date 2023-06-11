package Objects;

import help.Constants;

public class Tower {
    private int x,y, id, towerType;
    private float dmg, range, cooldown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }

    private void setDefaultCooldown() {
        cooldown = Constants.Towers.getDefaultCooldown(towerType);
    }

    private void setDefaultRange() {
        range = Constants.Towers.getDefaultRange(towerType);
    }

    private void setDefaultDmg() {
       dmg = Constants.Towers.getStartDmg(towerType);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getTowerType() {
        return towerType;
    }

    public float getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }
}
