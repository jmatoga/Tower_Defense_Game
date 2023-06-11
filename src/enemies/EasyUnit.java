package enemies;

import managers.EnemyManager;

import static help.Constants.Enemies.EASY_UNIT;

public class EasyUnit extends Enemy{
    public EasyUnit(float x, float y, int ID, EnemyManager em){
        super(x,y,ID,EASY_UNIT,em);
    }
}
