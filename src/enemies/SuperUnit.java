package enemies;

import managers.EnemyManager;

import static help.Constants.Enemies.SUPER_UNIT;

public class SuperUnit extends Enemy{
    public SuperUnit(float x, float y, int ID, EnemyManager em){
        super(x,y,ID,SUPER_UNIT,em);
    }
}
