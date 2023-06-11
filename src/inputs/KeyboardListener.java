package inputs;

import main.Game;
import main.GameStates;
import ui.ActionBar;
import ui.Bar;
import scenes.Playing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

public class KeyboardListener implements KeyListener {

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.out.println("Escape is pressed");
        else if (e.getKeyCode() == KeyEvent.VK_P)
           game.getPlaying().getActionBar().togglePause();
//        else if (e.getKeyCode() == KeyEvent.VK_A)
//            GameStates.gameState = MENU;
//        else if (e.getKeyCode() == KeyEvent.VK_S)
//            GameStates.gameState = SETTINGS;
//        else if (e.getKeyCode() == KeyEvent.VK_D)
//            GameStates.gameState = PLAYING;

        if (GameStates.gameState == PLAYING)
            game.getPlaying().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
