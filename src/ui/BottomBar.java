package ui;

import Objects.Tile;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class BottomBar {
    private int x, y, width, height;
    private MyButton bMENU;
    private Playing playing;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    private Tile selectedTile;

    public BottomBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;

        intButtons();
    }

    private void intButtons() {
        bMENU = new MyButton("MENU", 1040, 890, 150, 50);

        int w = 70;
        int h = 70;
        int xStart = 12;
        int yStart = 765;
        int xOffset = (int) (w * 1.1f);
        int i = 0;

        for (Tile tile : playing.getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
            i++;
        }
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g);

        drawTileButtons(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null){
            g.drawImage(selectedTile.getSprite(),1065,770,100,100,null);
            g.setColor(Color.black);
            g.drawRect(1065,770,100,100);
        }
    }

    private void drawTileButtons(Graphics g) {
        for (MyButton b : tileButtons) {
            // Image / Sprite
            g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

            // MouseOver
            if(b.isMouseOver())
                g.setColor(Color.red);
            else if(!b.isMouseOver())
                g.setColor(Color.black);

            // Border
            g.drawRect(b.x,b.y,b.width,b.height);

            // TODO nie podoba mi sie to ze tutaj jak sie kliknie i nie ruszy myszki to nie ma mouseover tylko jakby nie wykrywalo myszki i dopiero trzeba przesunac
            // MousePressed
            if(b.isMousePress()) {
                // żeby nie trzba było ruszać myszką żeby border się zmienił po kliknięciu kilka razy
                g.setColor(Color.red);
                g.drawRect(b.x,b.y,b.width,b.height);

                g.drawRect(b.x+1,b.y+1,b.width-2,b.height-2);
                g.drawRect(b.x+2,b.y+2,b.width-4,b.height-4);
            }
        }
    }

    public BufferedImage getButtImg(int id) {
        return playing.getTileManager().getSprite(id);
    }

    // TODO: wybór koloru
    public void draw(Graphics g) {

        // Buttons
        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            SetGameState(MENU);
        else {
            for(MyButton b : tileButtons) {
                if(b.getBorders().contains(x,y)) {
                    selectedTile = playing.getTileManager().getTile(b.getId());
                    playing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);

        for(MyButton b : tileButtons)
            b.setMouseOver(false);

        if (bMENU.getBorders().contains(x, y))
            bMENU.setMouseOver(true);
        else {
            for(MyButton b : tileButtons) {
                if(b.getBorders().contains(x,y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            bMENU.setMousePress(true);
        else {
            for(MyButton b : tileButtons) {
                if(b.getBorders().contains(x,y)) {
                    b.setMousePress(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        bMENU.resetBooleans(); // reset buttons
        for(MyButton b : tileButtons)
            b.resetBooleans();
    }
}
