package jvolpecastro;
import robocode.*;

public class Renault12 extends JuniorRobot {

    private Strategy strategist;

    public Renault12() {
        this.strategist = TacheroStrategist.getInstance();  // Usa Singleton
    }

    @Override
    public void run() {
        setColors(blue, blue, yellow, black, yellow);
        while(true) {
            strategist.run(this);  // Delegamos en el estratega
        }
    }

    @Override
    public void onScannedRobot() {
        strategist.onScannedRobot(this);
    }

    @Override
    public void onHitByBullet() {
        strategist.onHitByBullet(this);
    }

    @Override
    public void onHitWall() {
        strategist.onHitWall(this);
    }

    @Override
    public void onHitRobot() {
        strategist.onHitRobot(this);
    }
}
