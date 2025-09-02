package laboratorio;
import robocode.*;


public class LaboRobot extends JuniorRobot
{
    private Strategy strategy;

    public LaboRobot(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
	public void run() {

		setColors(orange, blue, white, yellow, black);
		strategy.run(this);
		
	}

    public Strategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		this.strategy.onScannedRobot(this);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet() {
		this.strategy.onHitByBullet(this);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall() {
		this.strategy.onHitWall(this);
	}	
}