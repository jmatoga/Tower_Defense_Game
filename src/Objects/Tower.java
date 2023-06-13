package Objects;

import help.Constants;
import static help.Constants.Towers.*;

public class Tower {
    private int x,y, id, towerType,cdTick, dmg; // cdTick - cooldown tick
    private float range, cooldown;
    private int tier;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;

        if(towerType == help.Constants.Towers.CRAZY_CANNON)
            tier = 4;
        else
            tier = 1;

        setDefaultDmg();
        setDefaultRange();
        setDefaultCooldown();
    }

    public void update() {
        cdTick++;
    }

    public void upgradeTower() {
        this.tier++;

        switch (getTowerType()){
            case BASIC:
                setTowerType(EXTRA);
                break;
            case EXTRA:
                dmg += 2;
                range += 10;
                cooldown -= 2;
                break;
            case MACHINE:
                setTowerType(FAST_MACHINE);
                break;
            case FAST_MACHINE:
                dmg += 3;
                range += 5;
                cooldown -= 1;
                break;
            case BRUTAL:
                setTowerType(ONE_HIT);
                break;
            case ONE_HIT:
                dmg += 5;
                range += 5;
                cooldown -= 1;
                break;
            case OWN_TOWER:
                // TODO
        }
        //return 0;
    }

    public boolean isCooldownOver() {
        return cdTick >= cooldown;
    }

    public void resetCooldown() {
        cdTick = 0;
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

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public int getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }

    public int getTier() {
        return tier;
    }
}
