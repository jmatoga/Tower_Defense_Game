package enemies;

import managers.EnemyManager;

import static help.Constants.Enemies.TURBO_HARD_UNIT;

public class TurboHardUnit extends Enemy {
    public TurboHardUnit(float x, float y, int ID, EnemyManager em){
        super(x,y,ID,TURBO_HARD_UNIT,em);
        setHealth(20);
    }
}
