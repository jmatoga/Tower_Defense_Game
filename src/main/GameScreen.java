package main;

import javax.swing.JPanel;
import java.awt.*;


public class GameScreen extends JPanel{

    private Game game;
    private  Dimension size;


    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();
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
