package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            System.out.println("Escape is pressed");
        else if (e.getKeyCode() == KeyEvent.VK_P)
             System.out.println("P is pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
