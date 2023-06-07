package scenes;

import help.LevelBuild;
import main.Game;
import managers.TileManager;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;

public class Playing extends GameScene implements  SceneMethods{

    private int[][] lvl;
    private TileManager tileManager;

    private MyButton bMENU;

    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        intButtons();

    }

    private void intButtons() {

        bMENU = new MyButton("MENU", 1040, 890, 150, 50);

    }



    @Override
    public void render(Graphics g) {
        g.drawImage(tileManager.img_bg, 0,0, null);

        drawButtons(g);

        for(int y = 0; y < lvl.length; y++) {
            for(int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id),x*50,y*50,null);
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMENU.getBorders().contains(x,y)){
            SetGameState(MENU);
        }

    }

    @Override
    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);


        if(bMENU.getBorders().contains(x,y)){
            bMENU.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMENU.getBorders().contains(x,y)){
            bMENU.setMousePress(true);
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bMENU.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g);
    }
}
