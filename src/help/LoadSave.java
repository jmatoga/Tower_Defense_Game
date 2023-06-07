package help;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static BufferedImage getSpriteResource() {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/texture_pack_1.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage getImageBG() {
        BufferedImage img_bg = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream("res/tlo.png");

        try {
            img_bg = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
        return img_bg;
    }
}
