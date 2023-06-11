package enemies;

import managers.EnemyManager;

import static help.Constants.Enemies.HARD_UNIT;

public class HardUnit extends Enemy{
    public HardUnit(float x, float y, int ID, EnemyManager em){
        super(x,y,ID,HARD_UNIT,em);
    }
}
