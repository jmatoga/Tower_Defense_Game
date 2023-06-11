package enemies;

import static help.Constants.Enemies.NORMAL_UNIT;

public class NormalUnit extends Enemy {
    public NormalUnit(float x, float y, int ID){
        super(x,y,ID,NORMAL_UNIT);
        setStartHealth();
    }
}
