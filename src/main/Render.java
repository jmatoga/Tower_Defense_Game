package main;

import java.awt.*;

public class Render {
    private GameScreen gameScreen;
    public Render(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void render(Graphics g) {
        switch(GameStates.gameState) {

            case MENU:
                break;
            case PLAYING:
                break;
            case SETTINGS:
                break;

        }
    }

    private void loadSprites() {
        for(int y=0; y<10;y++) {
            //spirites.add @Krzsiu
        }
    }
}
