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
    private BufferedImage img_bg;
    private ArrayList<BufferedImage> spirites = new ArrayList<>();
    private Random random;
    public Menu(Game game) {
        super(game);
        random = new Random();
        importImgage();
        importBG();
        loadSpirites();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img_bg, 0, 0, null);
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 24; x++) {
                g.drawImage(spirites.get(getRndInt()), x * 50, y * 50, null);
            }
        }
    }
    private void importImgage() {
        InputStream is = getClass().getResourceAsStream("/res/texture_pack_1.png");

        try {
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void importBG() {
        InputStream is = getClass().getResourceAsStream("/res/tlo.png");

        try {
            img_bg = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadSpirites()
    {
        for(int y = 0; y < 10; y++)
        {
            for(int x = 0; x < 10; x++)
            {
                spirites.add(img.getSubimage(x*50, y*50, 50,50));
            }
        }
    }

    private int getRndInt()
    {
        return random.nextInt(20);
    }


}
