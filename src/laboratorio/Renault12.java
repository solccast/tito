package laboratorio;
import robocode.*;


public class Renault12 extends JuniorRobot
{
    private Strategy strategy;

    public Renault12() {
        this.strategy = checkStrategy();
    }

    private Strategy checkStrategy() {
        return (this.others > 10 && this.energy > 50) ? new EvasiveStrategy() : new DamageStrategy();
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
}