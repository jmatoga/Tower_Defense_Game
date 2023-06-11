package ui;

import Objects.Tower;
import scenes.Playing;
import java.awt.*;
import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar{
    private MyButton bMENU;
    private Playing playing;
    private MyButton[] towerButtons;
    private Tower selectedTower; //obecnie wybrana wieża (gdy chcemy ją postawić)
    private Tower displayedTower; //gdy chcemy wyświetlić

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
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getId()],
                    1065,770,50,50, null);
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
