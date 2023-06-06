package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame
{
    private GameScreen gameScreen;
    private BufferedImage img;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public Game() {

        importImgage();


            setSize(1200, 950); //Robimy na takich wymairach
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            gameScreen = new GameScreen(img);
            add(gameScreen);
    }

    private void initInputs() {
        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus(); // żeby nie było błędów że nie ma focusa i nie pobiera inputów
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
        game.initInputs();


    }

}



