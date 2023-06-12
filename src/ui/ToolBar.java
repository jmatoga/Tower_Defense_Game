package ui;

import Objects.Tile;
import help.Constants;
import help.LoadSave;
import scenes.Editing;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static help.Constants.Tiles.*;
import static java.lang.Thread.*;
import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

public class ToolBar extends Bar{
    private Editing editing;
    private MyButton bMENU, bSave;
    private MyButton bPathStart, bPathEnd;
    private BufferedImage pathStart, pathEnd;
    private Tile selectedTile;
    private boolean isSavedToFile;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public ToolBar(int x, int y, int width, int height, Editing editing) {
        super(x, y, width, height);
        this.editing = editing;
        initPathImgs();
        intButtons();
    }
    private void initPathImgs(){
        pathStart = LoadSave.getSpriteResource().getSubimage(0*50,1*50,50,50);
        pathEnd = LoadSave.getSpriteResource().getSubimage(1*50,1*50,50,50);
    }
    private void intButtons() {
        bSave = new MyButton("SAVE", 880, 890, 150, 50);
        bMENU = new MyButton("MENU", 1040, 890, 150, 50);

        int w = 70;
        int h = 70;
        int xStart = 12;
        int yStart = 765;
        int xOffset = (int) (w * 1.1f);
        int i = 0;

        for (Tile tile : editing.getGame().getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getTileType(), xStart + xOffset * i, yStart, w, h, i));
            i++;
        }

        bPathStart = new MyButton("PathStart", xStart, yStart + xOffset, w, h, 3);
        bPathEnd = new MyButton("PathEnd", xStart + xOffset, yStart + xOffset, w, h, 4);


    }

    private void saveLevel() {
        editing.saveLevel();
        setIfIsSavedToFile(true);
    }

    private void showString(Graphics g) {
        g.setColor(Color.red);
        g.setFont(Constants.MyFont.setMyFont(60));
        g.drawString("Game has been saved!", 52, 748);

        g.setColor(Color.black);
        g.setFont(Constants.MyFont.setMyFont(60));
        g.drawString("Game has been saved!", 50,750);
    }

    private void drawButtons(Graphics g) {
        bMENU.draw(g, Constants.MyFont.SMALL_BUTTONS_SIZE);
        bSave.draw(g, Constants.MyFont.SMALL_BUTTONS_SIZE);

        drawPathButton(g,bPathStart,pathStart);
        drawPathButton(g,bPathEnd,pathEnd);

        bPathStart.draw(g, Constants.MyFont.TILE_BUTTONS_SIZE);
        bPathEnd.draw(g, Constants.MyFont.TILE_BUTTONS_SIZE);

        drawTileButtons(g);
        drawSelectedTile(g);
    }

    private void drawPathButton(Graphics g, MyButton b, BufferedImage pathStart) {
        g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);
        drawButtonFeedback(g,b);
    }

    public void draw(Graphics g) {
        // Buttons
        drawButtons(g);

        // String "Game is saved"
        if(isSavedToFile) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            showString(g);

            // Uruchamianie funkcji po upływie określonego czasu
            Runnable task = () -> {
                this.isSavedToFile = false;
                executor.shutdownNow(); // zamkniecie funkcji żeby nie powtarzała się co 3 sekundy
            };

            // Uruchamianie funkcji po 3 sekundach
            executor.schedule(task, 3, TimeUnit.SECONDS);

            // to sie buguje i zle dziala z tym
            // Zamykanie executora po pewnym czasie
            //long shutdownDelay = 4; // Czas w sekundach
//            try {
//                if (executor.awaitTermination(shutdownDelay, TimeUnit.SECONDS)) {
//                    // Jeśli executor nie zostanie zamknięty po określonym czasie, można go zakończyć siłą
//                    executor.shutdownNow();
//                }
//            } catch (InterruptedException e) {
//                // Obsługa przerwania
//                //e.printStackTrace();
//            }
        }
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

            drawButtonFeedback(g, b);
        }
    }
    public BufferedImage getButtImg(int id) {
        return editing.getGame().getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if (bMENU.getBorders().contains(x, y))
            SetGameState(MENU);
        else if(bSave.getBorders().contains(x,y))
            saveLevel();
        else if(bPathStart.getBorders().contains(x,y)) {
            selectedTile = new Tile(pathStart,3,-1);
            editing.setSelectedTile(selectedTile);
        }
        else if(bPathEnd.getBorders().contains(x,y)){
            selectedTile = new Tile(pathEnd,4,-2);
            editing.setSelectedTile(selectedTile);
        }
        else {
            for(MyButton b : tileButtons) {
                if(b.getBorders().contains(x,y)) {
                    selectedTile = editing.getGame().getTileManager().getTile(b.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMENU.setMouseOver(false);
        bSave.setMouseOver(false);
        bPathStart.setMouseOver(false);
        bPathEnd.setMouseOver(false);

        for(MyButton b : tileButtons)
            b.setMouseOver(false);

        if (bMENU.getBorders().contains(x, y))
            bMENU.setMouseOver(true);
        else if(bSave.getBorders().contains(x,y))
            bSave.setMouseOver(true);
        else if(bPathStart.getBorders().contains(x,y))
            bPathStart.setMouseOver(true);
        else if(bPathEnd.getBorders().contains(x,y))
            bPathEnd.setMouseOver(true);
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
        else if(bSave.getBorders().contains(x,y))
            bSave.setMousePress(true);
        else if(bPathStart.getBorders().contains(x,y))
            bPathStart.setMousePress(true);
        else if(bPathEnd.getBorders().contains(x,y))
            bPathEnd.setMousePress(true);
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
        bSave.resetBooleans();
        bPathStart.resetBooleans();
        bPathEnd.resetBooleans();
        for(MyButton b : tileButtons)
            b.resetBooleans();
    }

    public BufferedImage getStartPathImage() {
        return pathStart;
    }
    public BufferedImage getEndPathImage() {
        return pathEnd;
    }

    public void setIfIsSavedToFile(boolean setIfIsSavedToFile) {
        isSavedToFile = setIfIsSavedToFile;
    }
}
