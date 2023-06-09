package ui;

import scenes.Playing;
import java.awt.*;
import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ActionBar extends Bar{
    private MyButton bMENU;
    private Playing playing;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x,y,width,height);
        this.playing = playing;
        intButtons();
    }

    private void intButtons() {
        bMENU = new MyButton("MENU", 1040, 890, 150, 50);
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g);
    }


    // TODO: wybór koloru
    public void draw(Graphics g) {

        // Buttons
        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            SetGameState(MENU);
    }

    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);

        if (bMENU.getBorders().contains(x, y))
            bMENU.setMouseOver(true);
    }

    public void mousePressed(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            bMENU.setMousePress(true);
    }

    public void mouseReleased(int x, int y) {
        bMENU.resetBooleans(); // reset buttons
    }


}
