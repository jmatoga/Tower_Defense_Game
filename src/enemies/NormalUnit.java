package enemies;

import managers.EnemyManager;

import static help.Constants.Enemies.NORMAL_UNIT;

public class NormalUnit extends Enemy {
    public NormalUnit(float x, float y, int ID, EnemyManager em){
        super(x,y,ID,NORMAL_UNIT,em);
    }
}
