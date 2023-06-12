package scenes;

import help.Constants;
import main.Game;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class Settings extends GameScene implements SceneMethods {
    private MyButton bMENU;
    private BufferedImage img_bg;


    public Settings(Game game) {
        super(game);
        importBG();
        intButtons();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img_bg, 0, 0, null);
        drawButtons(g);
    }

    private void intButtons() {
        bMENU = new MyButton("MENU", 450, 740, 300, 100);
    }

    private void importBG() {
        InputStream is = getClass().getResourceAsStream("/res/menu_bg_2.png");

        try {
            img_bg = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        bMENU.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);
    }
}
