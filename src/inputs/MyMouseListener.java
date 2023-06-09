package inputs;

import main.Game;
import main.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private final Game game;

    public MyMouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) // lewy przycisk
        {
            switch (GameStates.gameState) {
                case MENU:
                    game.getMenu().mouseClicked(e.getX(), e.getY());
                    break;
                case PLAYING:
                    game.getPlaying().mouseClicked(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSettings().mouseClicked(e.getX(), e.getY());
                    break;
                case EDIT:
                    game.getSettings().mouseClicked(e.getX(),e.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mousePressed(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mousePressed(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getPlaying().mousePressed(e.getX(), e.getY());
                break;
            case EDIT:
                game.getSettings().mousePressed(e.getX(),e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mouseReleased(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseReleased(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseReleased(e.getX(), e.getY());
                break;
            case EDIT:
                game.getSettings().mouseReleased(e.getX(),e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mouseDragged(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseDragged(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseDragged(e.getX(), e.getY());
                break;
            case EDIT:
                game.getSettings().mouseDragged(e.getX(),e.getY());
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mouseMoved(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseMoved(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseMoved(e.getX(), e.getY());
                break;
            case EDIT:
                game.getSettings().mouseMoved(e.getX(),e.getY());
                break;
            default:
                break;
        }
    }
}
