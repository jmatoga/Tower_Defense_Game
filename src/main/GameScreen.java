package main;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameScreen extends JPanel{
    private Game game;

    public GameScreen(Game game) {
        this.game = game;


        //setPanelSize();
    }

    // private void setPanelSize() {}
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g); // renderujemy klase render
    }
}
