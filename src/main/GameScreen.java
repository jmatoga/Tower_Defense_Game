package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;



public class GameScreen extends JPanel {
    private Game game;
    private Dimension size;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus(); // żeby nie było błędów że nie ma focusa i nie pobiera inputów
    }

    private void setPanelSize() {
        size = new Dimension(1200, 950);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        }


    // private void setPanelSize() {}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g); // renderujemy klase render
    }
}
