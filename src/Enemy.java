public class Enemy {
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getRewardForDefeating() {
        return rewardForDefeating;
    }

    public void setRewardForDefeating(double rewardForDefeating) {
        this.rewardForDefeating = rewardForDefeating;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    private double speed; //ilość pixeli na sekunde? idk
    private double hp;
    //private double armor;
    private double rewardForDefeating;

    private enum EnemyType {
        TUTORIAL_UNIT,
        EASY_UNIT,
        NORMAL_UNIT,
        HARD_UNIT,
        SUPER_UNIT,
        TURBO_HARD_UNIT,
        OWN_UNIT
    }

    private EnemyType enemyType;

    public Enemy(EnemyType enemyType) {
        this.enemyType = enemyType;

        switch (enemyType) {
            case TUTORIAL_UNIT -> {
                this.speed = 60.0;
                this.hp = 20.0;
                this.rewardForDefeating = 1.0;
            }
            case EASY_UNIT -> {
                this.speed = 100.0;
                this.hp = 35.0;
                this.rewardForDefeating = 3.0;
            }
            case NORMAL_UNIT -> {
                this.speed = 200.0;
                this.hp = 60.0;
                this.rewardForDefeating = 9.0;
            }
            case HARD_UNIT -> {
                this.speed = 250.0;
                this.hp = 85.0;
                this.rewardForDefeating = 15.0;
            }
            case SUPER_UNIT -> {
                this.speed = 100.0;
                this.hp = 115.0;
                this.rewardForDefeating = 20.0;
            }
            case TURBO_HARD_UNIT -> {
                this.speed = 300.0;
                this.hp = 100.0;
                this.rewardForDefeating = 30.0;
            }
        }
    }

    public Enemy(double speed, double hp, double rewardForDefeating) {
        this.enemyType = EnemyType.OWN_UNIT;
        this.speed = speed;
        this.hp = hp;
        this.rewardForDefeating = rewardForDefeating;
    }
}
