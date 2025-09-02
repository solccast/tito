package laboratorio;
import robocode.*;


public class LaboRobot extends JuniorRobot
{
    private Strategy strategy;
    private Mode status;

    public LaboRobot() {
        this.status = Orchestrator.getInstance();
        this.strategy = status.checkMode(this);
    }

    @Override
    public void run() {
        setColors(black, yellow, black, yellow, red);
        while(true) {
            this.strategy = status.checkMode(this);
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