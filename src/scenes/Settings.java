package scenes;

import help.Constants;
import help.LoadSave;
import main.Game;
import managers.WaveManager;
import ui.MyButton;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class Settings extends GameScene implements SceneMethods {
    private MyButton bMENU, bSELECT_TUTORIAL,  bSELECT_NORMAL, bSELECT_HARD, bSAVE;
    private BufferedImage img_bg;
    private int Level;


    public Settings(Game game) {
        super(game);
        importBG();
        intButtons();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img_bg, 0, 0, null);
        drawButtons(g);
        drawDificultyLevel(g);
    }

    private void drawDificultyLevel(Graphics g) {

        g.setColor(Color.red);
        g.setFont(Constants.MyFont.setMyFont(60));
        g.drawString("Dificulty Level: ", 600, 400);

        switch (Level)
        {
            case 0:
                g.drawString("Normal", 750, 470);
                break;
            case 1:
                g.drawString("Hard", 780, 470);
                break;
            case 2:
                g.drawString("Tutorial", 730, 470);
                break;
            default:
                break;
        }
    }

    private void intButtons() {
        bMENU = new MyButton("MENU", 275, 740, 300, 100);

        bSAVE = new MyButton("SAVE", 625, 740, 300, 100);

        bSELECT_TUTORIAL = new MyButton("TUTORIAL", 150, 200, 300, 100);
        bSELECT_NORMAL = new MyButton("NORMAL", 150, 350, 300, 100);
        bSELECT_HARD = new MyButton("HARD", 150, 500, 300, 100);


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
        if (bSAVE.getBorders().contains(x, y)) {
            saveDifLevel();
        }
        if(bSELECT_NORMAL.getBorders().contains(x, y)){
            this.Level = 0;
            getGame().getPlaying().getWaveManager().setDifLev(0);

        }
        if(bSELECT_HARD.getBorders().contains(x, y)){
            this.Level = 1;
            getGame().getPlaying().getWaveManager().setDifLev(1);
            //getGame().getPlaying().setDificultyLevel(1);
        }
        if(bSELECT_TUTORIAL.getBorders().contains(x, y)){
            this.Level = 2;
            getGame().getPlaying().getWaveManager().setDifLev(2);
            //getGame().getPlaying().setDificultyLevel(2);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);
        bSAVE.setMouseOver(false);
        bSELECT_NORMAL.setMouseOver(false);
        bSELECT_HARD.setMouseOver(false);
        bSELECT_TUTORIAL.setMouseOver(false);

        if (bMENU.getBorders().contains(x, y)) {
            bMENU.setMouseOver(true);
        }
        if (bSAVE.getBorders().contains(x, y)) {
            bSAVE.setMouseOver(true);
        }
        if (bSELECT_NORMAL.getBorders().contains(x, y)) {
            bSELECT_NORMAL.setMouseOver(true);
        }
        if (bSELECT_HARD.getBorders().contains(x, y)) {
            bSELECT_HARD.setMouseOver(true);
        }
        if (bSELECT_TUTORIAL.getBorders().contains(x, y)) {
            bSELECT_TUTORIAL.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMENU.getBorders().contains(x, y)) {
            bMENU.setMousePress(true);
        }
        if (bSAVE.getBorders().contains(x, y)) {
            bSAVE.setMousePress(true);
        }
        if (bSELECT_NORMAL.getBorders().contains(x, y)) {
            bSELECT_NORMAL.setMousePress(true);
        }
        if (bSELECT_HARD.getBorders().contains(x, y)) {
            bSELECT_HARD.setMousePress(true);
        }
        if (bSELECT_TUTORIAL.getBorders().contains(x, y)) {
            bSELECT_TUTORIAL.setMousePress(true);
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
        bSAVE.resetBooleans();
        bSELECT_NORMAL.resetBooleans();
        bSELECT_HARD.resetBooleans();
        bSELECT_TUTORIAL.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);
        bSAVE.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);
        bSELECT_TUTORIAL.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);
        bSELECT_NORMAL.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);
        bSELECT_HARD.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);
    }

    public void saveDifLevel(){
        LoadSave.SaveDifLevel("Dif_lvl",Level);
        getGame().getPlaying().setDificultyLevel(Level);
    }
}
