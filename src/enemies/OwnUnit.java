package enemies;

import static help.Constants.Enemies.OWN_UNIT;

public class OwnUnit extends Enemy{
    public OwnUnit(float x, float y, int ID){
        super(x,y,ID,OWN_UNIT);
        setStartHealth();
    }
}
