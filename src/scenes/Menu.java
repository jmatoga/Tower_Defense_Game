package scenes;

import main.Game;
import ui.MyButton;

import static main.GameStates.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods {
    private BufferedImage img;
    private BufferedImage img_bg;
    private ArrayList<BufferedImage> spirites = new ArrayList<>();
    private Random random;
    private MyButton bPLAY, bEDIT, bSETTINGS, bQUIT;



    public Menu(Game game) {
        super(game);
        random = new Random();
        importBG();
        //importImgage();
        //loadSpirites();
        intButtons();
    }

    /*
     Funcja inicjujaca przyciski w menu
     */
    private void intButtons() {
        int w = 300, h = 100;
        int x = 450, y = 200;

        bPLAY = new MyButton("PLAY", x, y*1, w, h);
        bEDIT = new MyButton("EDIT", x, y*2, w, h);
        bSETTINGS = new MyButton("SETTINGS", x, y*3, w, h);
        bQUIT = new MyButton("QUIT", x, y*4, w, h);

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img_bg, 0, 0, null);
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPLAY.getBorders().contains(x, y)) {
            SetGameState(PLAYING);
        }

        if (bSETTINGS.getBorders().contains(x, y)) {
            SetGameState(SETTINGS);
        }

        if (bQUIT.getBorders().contains(x, y)) {
            System.exit(120); //taki sobie kod wybrałe wyjscia z przycisku
        }

        if (bEDIT.getBorders().contains(x, y)) {
            SetGameState(EDIT);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPLAY.setMouseOver(false);
        bSETTINGS.setMouseOver(false);
        bQUIT.setMouseOver(false);
        bEDIT.setMouseOver(false);

        if (bPLAY.getBorders().contains(x, y)) {
            bPLAY.setMouseOver(true);
        }
        if (bSETTINGS.getBorders().contains(x, y)) {
            bSETTINGS.setMouseOver(true);
        }
        if (bQUIT.getBorders().contains(x, y)) {
            bQUIT.setMouseOver(true);
        }
        if (bEDIT.getBorders().contains(x, y)) {
            bEDIT.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bPLAY.getBorders().contains(x, y)) {
            bPLAY.setMousePress(true);
        }
        if (bSETTINGS.getBorders().contains(x, y)) {
            bSETTINGS.setMousePress(true);
        }
        if (bQUIT.getBorders().contains(x, y)) {
            bQUIT.setMousePress(true);
        }
        if (bEDIT.getBorders().contains(x, y)) {
            bEDIT.setMousePress(true);
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
        bPLAY.resetBooleans();
        bSETTINGS.resetBooleans();
        bQUIT.resetBooleans();
        bEDIT.resetBooleans();
    }

    private void drawButtons(Graphics g) {
        bPLAY.draw(g);
        bSETTINGS.draw(g);
        bQUIT.draw(g);
        bEDIT.draw(g);
    }

    /*
    Importowanie texture packa

    private void importImgage() {
        InputStream is = getClass().getResourceAsStream("/res/texture_pack_1.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    /**
    Importowanie tła MENU
     */
    private void importBG() {
        InputStream is = getClass().getResourceAsStream("/res/menu_bg.png");

        try {
            img_bg = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    Ładowanie Spiritsów z texture packa

    //Chyba nie jest potrzebna już albo na razie//
    private void loadSpirites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                spirites.add(img.getSubimage(x * 50, y * 50, 50, 50));
            }
        }
    }
    */
    private int getRndInt() {

        return random.nextInt(20);
    }
}
