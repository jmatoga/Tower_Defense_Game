package ui;

import Objects.Tower;
import scenes.Playing;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar{
    private MyButton bMENU;
    private Playing playing;
    private MyButton[] towerButtons;
    private Tower selectedTower; //obecnie wybrana wieża (gdy chcemy ją postawić)
    private Tower displayedTower; //gdy chcemy wyświetlić

    private Font pixelFont;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x,y,width,height);
        this.playing = playing;
        intButtons();
    }

    private void intButtons() {
        bMENU = new MyButton("MENU", 1040, 890, 150, 50);
        towerButtons = new MyButton[3]; //liczba wież

        int w = 70;
        int h = 70;
        int xStart = 12;
        int yStart = 765;
        int xOffset = (int) (w * 1.1f);

        for(int i = 0; i < towerButtons.length; i++){
            towerButtons[i] = new MyButton("",xStart+xOffset*i,yStart,w,h,i);
        }
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g);

        for(MyButton b : towerButtons) {
            g.setColor(Color.white);
            g.fillRect(b.x,b.y,b.width,b.height); //dodanie tła
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null);
            drawButtonFeedback(g, b);
        }
    }


    // TODO: wybór koloru
    public void draw(Graphics g) {

        // Buttons
        drawButtons(g);

        //DisplayedTower
        drawDisplayTower(g);
    }

    /**
     * Wyświetlanie informacji o wieży
     * @param g Obiekt Graphics na którym rysujemy
     */
    private void drawDisplayTower(Graphics g) {
        if(displayedTower != null){
            g.setColor(Color.decode("0x7f5415"));
            g.fillRect(850, 780, 300,70);
            g.setColor(Color.white);
            g.drawRect(850, 780, 300,70);
            g.drawRect(850, 780, 300,70);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getId()],
                    850,780,70,70, null);
            fontSetter();
            g.setFont(pixelFont);
            g.drawString("Tower",930, 820);
            drawDisplaydTowerBorder(g);
            drawDisplaydTowerRange(g);
        }
    }

    /**
     * Funckja rysuje zasieg danej wiezy
     * @param g
     */
    private void drawDisplaydTowerRange(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(displayedTower.getX() + 25 - (int)(displayedTower.getRange()*2)/2,
                displayedTower.getY() + 25 - (int)(displayedTower.getRange()*2)/2,
                (int)displayedTower.getRange()*2, (int)displayedTower.getRange()*2);

    }

    /**
     * Funckja robi obramówke zaznaczenia wieży na polu gry
     * @param g
     */
    private void drawDisplaydTowerBorder(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 50, 50);
    }

    private void fontSetter(){
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/PixelMplus12-Regular.ttf")).deriveFont(Font.BOLD, 30f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param t
     */
    public void displayTower(Tower t) {
        displayedTower = t;
    }
    public void mouseClicked(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            SetGameState(MENU);
        else{
            for(MyButton b : towerButtons){
                if(b.getBorders().contains(x,y)){
                    selectedTower = new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);

        if (bMENU.getBorders().contains(x, y))
            bMENU.setMouseOver(true);
        else{
            for(MyButton b : towerButtons) {
                b.setMouseOver(false);
                if (b.getBorders().contains(x,y))
                    b.setMouseOver(true);
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            bMENU.setMousePress(true);
        else
            for(MyButton b : towerButtons)
                if (b.getBorders().contains(x,y))
                    b.setMouseOver(true);

    }

    public void mouseReleased(int x, int y) {
        bMENU.resetBooleans(); // reset buttons
        for(MyButton b : towerButtons)
                b.resetBooleans();
    }

}
