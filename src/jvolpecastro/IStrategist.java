package jvolpecastro;

import robocode.JuniorRobot;

public interface IStrategist {
    public abstract void run(JuniorRobot robot);
    public abstract void onScannedRobot(JuniorRobot robot);
    public abstract void onHitByBullet(JuniorRobot robot);
    public abstract void onHitRobot(JuniorRobot robot);
    public void onHitWall(JuniorRobot robot);
}
