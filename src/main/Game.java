package main;

import help.LoadSave;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import managers.EnemyManager;
import managers.TileManager;
import scenes.*;
import scenes.Menu;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {
    private final double setFPS = 120.0;
    private final double setUPS = 60.0;

    private GameScreen gameScreen;
    private Thread gameThread;

    // Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Editing editing;
    private GameOver gameOver;

    private TileManager tileManager;
    private JPanel contentPane;

    public Game() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100,100,450,300); // - do suwaka
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Tower Defense Game // By Jakub Matoga, Krzysztof Popiela, Daniel Minior");

        // --- do suwaka
        //contentPane = new JPanel();
        //contentPane.setLayout(new BorderLayout(0,0));
        //contentPane.setBorder(new EmptyBorder(5,5,5,5));
        //setContentPane(contentPane);

        ////JTextArea textArea = new JTextArea(10,20);
        ////contentPane.add(gameScreen,BorderLayout.CENTER);
        //JScrollPane scroll = new JScrollPane(gameScreen,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ////textArea.setLineWrap(true);
        //contentPane.add(scroll);

        initClasses();
        createDefaultLevel();

        add(gameScreen);
        pack();

        setVisible(true);
    }

    /**
     * Tworzenie nowego poziomu z wartościami domyślnymi
     */
    private void createDefaultLevel(){
        int[] arr = new int[360];
        for(int i = 0; i < arr.length; i++){
            arr[i] = 0; //pierwsza tekstura
        }
        LoadSave.CreateLevel("new_Level",arr);
    }

    /**
    Rozpoczęcie nowego wątku
     */
    public void start() {
        gameThread = new Thread(this) {

        };
        gameThread.start();
    }

    /**
     Aktualizacja stanu gry
     */
    private void updateGame() {
        switch(GameStates.gameState){
            case PLAYING:
                playing.update();
                break;
            case MENU:
                break;
            case SETTINGS:
                break;
            case EDIT:
                break;
            default:
                break;
        }
    }

    /**
     Inicjacja klas potrzebnych do gry
     */
    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        editing = new Editing(this);
        gameOver = new GameOver(this);
    }

    public static void main(String[] args) {
        System.out.println("New game");
        Game game = new Game();
        game.gameScreen.initInputs();
        game.start();
    }

    // Getters and setters
    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }
    public Editing getEditor() {
        return editing;
    }
    public GameOver getGameOver() {
        return gameOver;
    }
    public TileManager getTileManager(){
        return tileManager;
    }

    /**
     * Wyświetlanie FPS i UPS po starcie gry
     */
    @Override
    public void run() {
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        double timePerFrame = 1000000000.0 / setFPS; //bilonnanosekund - tutaj 60 oznacza ile stałych fpsów chcemy mieć w grze
        double timePerUpdate = 1000000000.0 / setUPS;

        int frames = 0;
        int updates = 0;

        long now;

        while (true) {
            now = System.nanoTime();

            //render
            if (now - lastFrame >= timePerFrame) {
                lastFrame = now;
                repaint();
                lastFrame = now;
                frames++;
            }

            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            }

            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
}
