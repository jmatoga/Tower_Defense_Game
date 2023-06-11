package help;

public class Constants {
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

        public static final int TUTORIAL_UNIT = 0;
        public static final int EASY_UNIT = 1;
        public static final int NORMAL_UNIT = 2;
        public static final int HARD_UNIT = 3;
        public static final int SUPER_UNIT = 4;
        public static final int TURBO_HARD_UNIT = 5;
        public static final int OWN_UNIT = 6;

        public static float getSpeed(int enemyType){
            switch (enemyType){
                case TUTORIAL_UNIT:
                    return 0.4f;
                case EASY_UNIT:
                    return 0.6f;
                case NORMAL_UNIT:
                    return 0.5f;
                case HARD_UNIT:
                    return 0.3f;
                case SUPER_UNIT:
                    return 0.7f;
                case TURBO_HARD_UNIT:
                    return 0.65f;
                case OWN_UNIT:
                    return 0.75f;
            }
            return 0;
        }
    }

    public static class Towers{
        public static final int BASIC = 0; //typ prosty
        public static final int MACHINE = 1; //szybszy niż prosty
        public static final int BRUTAL = 2; //mocniejszy niż prosty
    }
}
