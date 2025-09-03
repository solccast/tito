package laboratorio;
import robocode.*;


public class Renault12 extends JuniorRobot
{
    private Strategy strategy;

    public Renault12() {
        this.strategy = checkStrategy();
    }

    private Strategy checkStrategy() {
        if ((this.others > 15 && this.energy > 80) || (this.energy < 20)) {
            return new EvasiveStrategy();
        } else {
            return new DamageStrategy();
        }
    }

    @Override
    public void run() {
        setColors(black, yellow, black, yellow, red);
        while(true) {
            this.strategy = checkStrategy();
            strategy.run(this);
        }
    }

    @Override
    public void onScannedRobot() {
        strategy.onScannedRobot(this);
    }

    @Override
    public void onHitByBullet() {
        strategy.onHitByBullet(this);
    }

    @Override
    public void onHitWall() {
        strategy.onHitWall(this);
    }

    @Override
    public void onHitRobot(){
        strategy.onHitRobot(this);
    }
}