package scenes;

import main.Game;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class Settings extends GameScene implements SceneMethods {
    private MyButton bMENU;

    public Settings(Game game) {
        super(game);
        intButtons();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(10, 0, 1200, 950);
        drawButtons(g);
    }

    private void intButtons() {
        bMENU = new MyButton("MENU", 1040, 890, 150, 50);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMENU.getBorders().contains(x, y)) {
            SetGameState(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);

        if (bMENU.getBorders().contains(x, y)) {
            bMENU.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMENU.getBorders().contains(x, y)) {
            bMENU.setMousePress(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bMENU.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g);
    }
}
