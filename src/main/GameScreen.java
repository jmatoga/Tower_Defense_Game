package main;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameScreen extends JPanel{

    private Random random;
    private BufferedImage img;

    private Render render;

    public GameScreen(BufferedImage img) {
        this.img = img;
        render = new Render(this);

        random = new Random();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(img,0, 0,null);



//        for(int x = 0; x < 40; x++) {
//            for (int y = 0; y < 30; y++) {
//                g.setColor(getRandomColor());
//                g.fillRect(x * 32, y*32, 32, 32);
//            }
//        }
    }

    private Color getRandomColor()
    {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r, g, b);

    }
}
