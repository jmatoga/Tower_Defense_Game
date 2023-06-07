package main;

public enum GameStates {
    PLAYING, MENU, SETTINGS;
    public static GameStates gameState = MENU;
    public static void SetGameState(GameStates state) {
        gameState = state;
    }
}
