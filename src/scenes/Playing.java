package scenes;

import help.LevelBuild;
import main.Game;
import managers.TileManager;
import ui.BottomBar;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private TileManager tileManager;

    private BottomBar bottomBar;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();

        // TODO: sprawdzic czy dobre rozmiary
        bottomBar = new BottomBar(0, 1200, 750, 200, this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.img_bg, 0, 0, null);

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 50, y * 50, null);
            }
        }

        bottomBar.draw(g);
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    // TODO: idk czy takie wymiary
    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 200)
            bottomBar.mouseClicked(x, y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 200)
            bottomBar.mouseMoved(x, y);
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 200)
            bottomBar.mousePressed(x, y);
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }
}
