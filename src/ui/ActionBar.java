package ui;

import Objects.Tower;
import help.Constants;
import help.LoadSave;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar{
    private MyButton bMENU,bPause;
    private Playing playing;
    private MyButton[] towerButtons;
    private Tower selectedTower; //obecnie wybrana wieża (gdy chcemy ją postawić)
    private Tower displayedTower; //gdy chcemy wyświetlić
    //private Font pixelFont = Constants.MyFont.setMyFont(10);
    private int gold = 100; // pieniadze, zaczynamy z 100
    private boolean showTowerCost;
    private int towerCostType;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x,y,width,height);
        this.playing = playing;
        intButtons();
    }

    private void intButtons() {
        bMENU = new MyButton("MENU", 1000, 840, 150, 40);
        bPause = new MyButton("PAUSE", 1000, 890, 150, 40);

        towerButtons = new MyButton[3]; //liczba wież

        int w = 70;
        int h = 70;
        int xStart = 50;
        int yStart = 780;
        int xOffset = (int) (w * 1.2f);

        for(int i = 0; i < towerButtons.length; i++){
            towerButtons[i] = new MyButton("",xStart+xOffset*i,yStart,w,h,i);
        }
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g, Constants.MyFont.SMALL_BUTTONS_SIZE);
        bPause.draw(g, Constants.MyFont.SMALL_BUTTONS_SIZE);

        //BufferedImage atlas = LoadSave.getSpriteResource();
        //BufferedImage img_bg = atlas.getSubimage(8*50,2*50,50,50);


        for(MyButton b : towerButtons) {
            g.setColor(Color.decode("0x7f5415"));
            g.fillRect(b.x,b.y,b.width,b.height); //dodanie tła
            g.setColor(Color.black);
            g.drawRect(b.x, b.y, b.width, b.height);
            //g.drawImage(img_bg, b.x, b.y, null);
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

        // Wave info
        // TODO

        // Gold info
        drawGoldAmount(g);

        // Draw Tower Cost
        if(showTowerCost)
            drawTowerCost(g);

        // Game Paused text
        if(playing.isGamePaused()) {
            g.setColor(Color.red);
            g.setFont(Constants.MyFont.setMyFont(60));
            g.drawString("Game is paused!", 52, 748);

            g.setColor(Color.black);
            g.setFont(Constants.MyFont.setMyFont(60));
            g.drawString("Game is paused!", 50,750);

        }
    }

    /**
     * Wyświetlanie informacji o koszcie wieży
     * @param g Obiekt Graphics na którym rysujemy
     */
    private void drawTowerCost(Graphics g) {
        g.setFont(Constants.MyFont.setMyFont(26));
        g.setColor(Color.gray);
        g.fillRect(400,790,160,100);
        g.setColor(Color.black);
        g.drawRect(400,790,160,100);
        g.drawString("" + getTowerCostName(),412,825);
        g.drawString("Cost: " + getTowerCostCost() + "g",412,865);

        // Show this if player lack gold for the selected tower
        if(isTowerCostMoreThanCurrentGold()) {
            g.setColor(Color.red);
            g.drawString("You can't afford!",365,920);
        }
    }

    private boolean isTowerCostMoreThanCurrentGold() {
        return getTowerCostCost() > gold;
    }

    private String getTowerCostName() {
        return Constants.Towers.GetName(towerCostType);
    }

    private int getTowerCostCost() {
        return Constants.Towers.GetTowerCost(towerCostType);
    }

    /**
     * Wyświetlanie informacji o pieniadzach (goldzie)
     * @param g Obiekt Graphics na którym rysujemy
     */
    private void drawGoldAmount(Graphics g) {
        g.setColor(Color.decode("#AE8625"));
        g.setFont(Constants.MyFont.setMyFont(45));
        g.drawString("Gold: " + gold,60,895);
    }

    /**
     * Wyświetlanie informacji o wieży
     * @param g Obiekt Graphics na którym rysujemy
     */
    private void drawDisplayTower(Graphics g) {
        if(displayedTower != null){
            g.setFont(Constants.MyFont.setMyFont(20));
            g.setColor(Color.decode("0x7f5415"));
            g.fillRect(810, 780, 180,150);
            g.setColor(Color.lightGray);
            g.drawRect(810, 780, 180,150);
            g.drawRect(810, 780, 180,150);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()],
                    820,780,70,70, null);
            g.drawString("Tower",900, 820);
            drawDisplaydTowerBorder(g);
            drawDisplaydTowerRange(g);
        }
    }

    /**
     * Funckja rysuje zasieg danej wiezy
     * @param g
     */
    private void drawDisplaydTowerRange(Graphics g) {
        g.setColor(Color.lightGray);
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

    /**
     *
     * @param t
     */
    public void displayTower(Tower t) {
        displayedTower = t;
    }

    public void togglePause() {
        playing.setGamePaused(!playing.isGamePaused()); // zamiana na przeciwne

        if(playing.isGamePaused())
            bPause.setText("UNPAUSE");
        else
            bPause.setText("PAUSE");
    }

    public void mouseClicked(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            SetGameState(MENU);
        else if(bPause.getBorders().contains(x,y))
            togglePause();
        else{
            for(MyButton b : towerButtons){
                if(b.getBorders().contains(x,y)){
                    if(!isGoldEnoughForTower(b.getId()))
                        return;
                    
                    selectedTower = new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    private boolean isGoldEnoughForTower(int towerType) {
        return gold >= Constants.Towers.GetTowerCost(towerType);
    }

    /**
     * Gdy mysz nad obiektem
     * @param x - szerokosc
     * @param y - wysokosc
     */
    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);
        bPause.setMouseOver(false);
        showTowerCost = false;

        if (bMENU.getBorders().contains(x, y))
            bMENU.setMouseOver(true);
        else if(bPause.getBorders().contains(x,y))
            bPause.setMouseOver(true);
        else{
            for(MyButton b : towerButtons) {
                b.setMouseOver(false);
                if (b.getBorders().contains(x,y)) {
                    b.setMouseOver(true);
                    showTowerCost = true;
                    towerCostType = b.getId();
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            bMENU.setMousePress(true);
        else if(bPause.getBorders().contains(x,y))
            bPause.setMousePress(true);
        else
            for(MyButton b : towerButtons)
                if (b.getBorders().contains(x,y))
                    b.setMouseOver(true);
    }

    public void mouseReleased(int x, int y) {
        bMENU.resetBooleans(); // reset buttons
        bPause.resetBooleans();
        for(MyButton b : towerButtons)
                b.resetBooleans();
    }

    public void payForTower(int towerType) {
        this.gold -= Constants.Towers.GetTowerCost(towerType);
    }

    public void addGold(int getReward) {
        this.gold += getReward;

    }
}
