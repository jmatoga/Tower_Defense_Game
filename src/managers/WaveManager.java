package managers;

import events.Wave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1; // 1 sekunda
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex,waveIndex;

    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }

    public void update() {
        if(enemySpawnTick < enemySpawnTickLimit)
            enemySpawnTick++;
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,1))));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemiesInWave() {
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }
}
