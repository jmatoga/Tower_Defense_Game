import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame
{
    private GameScreen gameScreen;
    private BufferedImage img;

    public Game() {

        importImgage();


            setSize(1294, 998); //Robimy na takich wymairach
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            gameScreen = new GameScreen(img);
            add(gameScreen);
    }

    private void importImgage() {
        InputStream is = getClass().getResourceAsStream("/res/tlo.png");

        try {
            img = ImageIO.read(is);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println("New game");

        Game game = new Game();

    }

}



