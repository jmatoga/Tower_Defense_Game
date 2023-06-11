package enemies;

import managers.EnemyManager;

import static help.Constants.Enemies.OWN_UNIT;

public class OwnUnit extends Enemy{
    public OwnUnit(float x, float y, int ID, EnemyManager em){
        super(x,y,ID,OWN_UNIT,em);
    }
}
