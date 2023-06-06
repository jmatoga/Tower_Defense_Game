package scenes;

import main.Game;

import java.awt.*;

public class Settings extends GameScene implements  SceneMethods{
    public Settings(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(10,0,640,640);
    }
}
