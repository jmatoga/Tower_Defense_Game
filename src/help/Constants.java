package help;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Constants {
    public static class MyFont {
        public static final float BIG_BUTTONS_SIZE = 60;
        public static final float SMALL_BUTTONS_SIZE = 30;
        public static final float TILE_BUTTONS_SIZE = 14;

        public static Font pixelFont;

        public static Font setMyFont(float size) {
            try {
                pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/res/PixelMplus12-Regular.ttf")).deriveFont(Font.BOLD, size);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            return pixelFont;
        }
    }

    public static class Direction{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class Tiles{
        public static final int ROAD_TILE = 0;
        public static final int OTHER_TILE = 1;
    }

    public static class Enemies {
        public static final int EASY_UNIT = 0;
        public static final int NORMAL_UNIT = 1;
        public static final int HARD_UNIT = 2;
        public static final int SUPER_UNIT = 3;
        public static final int TURBO_HARD_UNIT = 4;
        public static final int TUTORIAL_UNIT = 5;
        public static final int OWN_UNIT = 6;

        public static int getReward(int enemyType) {
            switch (enemyType){
                case TUTORIAL_UNIT:
                    return 1;
                case EASY_UNIT:
                    return 5;
                case NORMAL_UNIT:
                    return 10;
                case HARD_UNIT:
                    return 20;
                case SUPER_UNIT:
                    return 40;
                case TURBO_HARD_UNIT:
                    return 45;
                case OWN_UNIT:
                    //TODO
                    return 1;
            }
            return 0;
        }

        public static float getSpeed(int enemyType){
            switch (enemyType){
                case TUTORIAL_UNIT:
                    return 0.12f;
                case EASY_UNIT:
                    return 0.2f;
                case NORMAL_UNIT:
                    return 0.4f;
                case HARD_UNIT:
                    return 0.5f;
                case SUPER_UNIT:
                    return 0.2f;
                case TURBO_HARD_UNIT:
                    return 0.6f;
                case OWN_UNIT:
                    return 0.75f;
            }
            return 0;
        }

        public static int GetStartHealth(int enemyType){
            switch (enemyType){
                case TUTORIAL_UNIT:
                    return 20;
                case EASY_UNIT:
                    return 35;
                case NORMAL_UNIT:
                    return 60;
                case HARD_UNIT:
                    return 85;
                case SUPER_UNIT:
                    return 115;
                case TURBO_HARD_UNIT:
                    return 100;
                case OWN_UNIT:
                    return 10;
            }
            return  0;
        }

//        public static int getReward(int enemyType) {
//            return 0;
//        }
    }

    public static class Towers{
        public static final int BASIC = 0; //typ BASIC
        public static final int MACHINE = 1; //szybszy niż BASIC
        public static final int BRUTAL = 2; //mocniejszy niż BASIC
        public static final int EXTRA = 3; //ulepszony BASIC
        public static final int FAST_MACHINE = 4; //szybszy niż MACHINE
        public static final int ONE_HIT = 5; //mocniejszy niż BRUTAL
        public static final int CRAZY_CANNON = 6; //najmocniejsza wieża
        public static final int DEUS_EX_MACHINA = 7; //testowy
        public static final int OWN_TOWER = 8;

        public static int GetTowerCost(int towerType) {
            switch (towerType){
                case BASIC:
                    return 30;
                case MACHINE:
                    return 45;
                case BRUTAL:
                    return 60;
                case EXTRA:
                    return (int) (30*1.6f); // koszt wiezy + upgrade
                case FAST_MACHINE:
                    return (int) (45*1.6f); // koszt wiezy + upgrade
                case ONE_HIT:
                    return (int) (60*1.6f); // koszt wiezy + upgrade
                case CRAZY_CANNON:
                    return 999;
                case DEUS_EX_MACHINA:
                    return 450;
                case OWN_TOWER:
                    // TODO
                    return 1;
            }
            return 0;
        }

        public static String GetName(int towerType){
            switch (towerType){
                case BASIC:
                    return "Basic";
                case MACHINE:
                    return "Machine";
                case BRUTAL:
                    return "Brutal";
                case EXTRA:
                    return  "Extra";
                case FAST_MACHINE:
                    return  "Fast Machine";
                case ONE_HIT:
                    return  "One Hit";
                case CRAZY_CANNON:
                    return  "Crazy";
                case DEUS_EX_MACHINA:
                    return  "Deus Ex Machina";
                case OWN_TOWER:
                    return "Own Tower";
            }
            return null;
        }

        public static int getStartDmg(int towerType){
            switch (towerType){
                case BASIC:
                    return 10;
                case MACHINE:
                    return 10;
                case BRUTAL:
                    return 30;
                case EXTRA:
                    return 30;
                case FAST_MACHINE:
                    return 25;
                case ONE_HIT:
                    return 120;
                case CRAZY_CANNON:
                    return 20;
                case DEUS_EX_MACHINA:
                    return 500;
                case OWN_TOWER:
                    return 1;
            }
            return 0;
        }

        public static float getDefaultRange(int towerType){
            switch (towerType){
                case BASIC:
                    return 200;
                case MACHINE:
                    return 240;
                case BRUTAL:
                    return 240;
                case EXTRA:
                    return 280;
                case FAST_MACHINE:
                    return 320;
                case ONE_HIT:
                    return 240;
                case CRAZY_CANNON:
                    return 400;
                case DEUS_EX_MACHINA:
                    return 400;
                case OWN_TOWER:
                    return 1;
            }
            return 0;

        }

        public static float getDefaultCooldown(int towerType){
            switch (towerType){
                case BASIC:
                    return 100f;
                case MACHINE:
                    return 30f;
                case BRUTAL:
                    return 100f;
                case EXTRA:
                    return 30f;
                case FAST_MACHINE:
                    return 10f;
                case ONE_HIT:
                    return 66f;
                case CRAZY_CANNON:
                    return 10f;
                case DEUS_EX_MACHINA:
                    return 1f;
                case OWN_TOWER:
                    return 1;
            }
            return 0;

        }
    }

    public static class Projectiles {
        public static final int BASIC_AMMO = 0; //typ BASIC
        public static final int MACHINE_AMMO = 1; //typ MACHINE
        public static final int HEAVY_AMMO = 2; //typ BRUTAL
        public static final int CRAZY_AMMO = 3; //typ CRAZY
        public static final int DEM_AMMO = 4; //typ DEUS EX MACHINA
        public static final int OWN_AMMO = 5;

        public static float GetSpeed(int type){
            switch(type){
                case BASIC_AMMO:
                    return 4f;
                case MACHINE_AMMO:
                    return 6f;
                case HEAVY_AMMO:
                    return 4f;
                case CRAZY_AMMO:
                    return 4f;
                case DEM_AMMO:
                    return 10f;
                case OWN_AMMO:
                    return 10f;
            }
            return 0f;
        }
    }
}
