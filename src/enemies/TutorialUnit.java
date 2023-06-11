package enemies;

import managers.EnemyManager;

import static help.Constants.Enemies.TUTORIAL_UNIT;

public class TutorialUnit extends Enemy{
    public TutorialUnit(float x, float y, int ID, EnemyManager em){
        super(x,y,ID,TUTORIAL_UNIT,em);
    }

}
