package scenes;

import help.Constants;
import main.Game;
import ui.MyButton;
import static main.GameStates.*;

import java.awt.*;

public class YouWin extends GameScene implements SceneMethods{

    private MyButton bReplay, bMenu;

    public YouWin(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 300, h = 100;
        int x = 450, y = 240;

        bReplay = new MyButton("REPLAY", x, y*2, w, h);
        bMenu = new MyButton("MENU", x, y*3, w, h);


    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0,0,1200, 950);

        g.setColor(Color.GREEN);
        g.setFont(Constants.MyFont.setMyFont(170));
        g.drawString("YOU WIN!", 210, 300);

        bMenu.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);
        bReplay.draw(g, Constants.MyFont.BIG_BUTTONS_SIZE);

    }


    private void replayGame() {
        resetAll();
        SetGameState(PLAYING);
    }

    private void resetAll(){
        getGame().getPlaying().resetEverythig();
    }
    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBorders().contains(x,y)) {
            resetAll();
            SetGameState(MENU);
        }
        else if (bReplay.getBorders().contains(x, y))
            replayGame();
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBorders().contains(x,y))
            bMenu.setMouseOver(true);
        else if (bReplay.getBorders().contains(x, y))
            bReplay.setMouseOver(true);

    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBorders().contains(x,y))
            bMenu.setMousePress(true);
        else if (bReplay.getBorders().contains(x, y))
            bReplay.setMousePress(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
