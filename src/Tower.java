public class Tower {
    private double fireSpeed; //okreslnae ile razy na sekunde
    private double fireDamage;
    private double cost;
    private double fireRange; //okreśłona jako promien kołana obszar? podane w poixelach np? idk

    public double getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(double fireSpeed) {
        this.fireSpeed = fireSpeed;
    }

    public double getFireDamage() {
        return fireDamage;
    }

    public void setFireDamage(double fireDamage) {
        this.fireDamage = fireDamage;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getFireRange() {
        return fireRange;
    }

    public void setFireRange(double fireRange) {
        this.fireRange = fireRange;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public void setTowerType(TowerType towerType) {
        this.towerType = towerType;
    }

    private enum TowerType
    {
        BASIC, //typ prosty
        MACHINE, //szybszy niż prosty
        BRUTAL, //mocniejszy niż prosty
        EXTRA, //BASIC level up
        FAST_MACHINE, //szybszy niż machine
        ONE_HIT, //mocniejszy niż brutal
        CARZY_CANNON, //najmocniejsza wieża
        DEUS_EX_MACHINE, //admin mode
        OWN_TOWER
    }

    private TowerType towerType;

    public Tower(TowerType towerType) {
        this.towerType = towerType;

        switch (towerType) {
            case BASIC -> {
                this.fireSpeed = 0.66;
                this.fireDamage = 10.0;
                this.cost = 10.0;
                this.fireRange = 50.0;
            }
            case MACHINE -> {
                this.fireSpeed = 2;
                this.fireDamage = 10.0;
                this.cost = 20.0;
                this.fireRange = 60.0;
            }
            case BRUTAL -> {
                this.fireSpeed = 0.66;
                this.fireDamage = 30.0;
                this.cost = 25.0;
                this.fireRange = 60.0;
            }
            case EXTRA -> {
                this.fireSpeed = 2.0;
                this.fireDamage = 30.0;
                this.cost = 40.0;
                this.fireRange = 70.0;
            }
            case FAST_MACHINE -> {
                this.fireSpeed = 6.0;
                this.fireDamage = 25.0;
                this.cost = 45.0;
                this.fireRange = 80.0;
            }
            case ONE_HIT -> {
                this.fireSpeed = 1.0;
                this.fireDamage = 120.0;
                this.cost = 60.0;
                this.fireRange = 60.0;
            }
            case CARZY_CANNON -> {
                this.fireSpeed = 6.0;
                this.fireDamage = 50.0;
                this.cost = 100.0;
                this.fireRange = 100.0;
            }
            case DEUS_EX_MACHINE -> {
                this.fireSpeed = 50.0;
                this.fireDamage = 500.0;
                this.cost = 1000.0;
                this.fireRange = 100.0;
            }
            default -> throw new IllegalArgumentException("Nieznany typ wieży");
        }
    }

    public Tower (double fireSpeed, double fireDamage, double cost, double fireRange) {
        this.towerType = TowerType.OWN_TOWER;
        this.fireSpeed = fireSpeed;
        this.fireDamage = fireDamage;
        this.cost = cost;
        this.fireRange = fireRange;
    }
}
