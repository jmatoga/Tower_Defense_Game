package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A)
            System.out.printf("A is pressed");
        else if (e.getKeyCode() == KeyEvent.VK_B)
             System.out.printf("B is pressed");

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
