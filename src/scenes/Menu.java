package scenes;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods {
    private BufferedImage img;
    private ArrayList<BufferedImage> spirites = new ArrayList<>();
    private Random random;
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImgage();
        loadSprites();
    }

    @Override
    public void render(Graphics g) {
        // do zrobienia @Krzysiu
        //public void paintComponent (Graphics g){
        //   super.paintComponent(g);

        // g.drawImage(img, 0, 0, null);


        //        for(int x = 0; x < 24; x++) {
        //            for (int y = 0; y < 19; y++) {
        //                g.setColor(getRandomColor());
        //                g.fillRect(x * 50, y*50, 50, 50);
        //            }
        //        }
        // }
    }

    private void importImgage() {
        InputStream is = getClass().getResourceAsStream("/res/tlo.png");

        try {
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadSprites() {
        for(int y=0; y<10;y++) {
            //spirites.add @Krzsiu
        }
    }


}
