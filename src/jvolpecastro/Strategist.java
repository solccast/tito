package jvolpecastro;
import robocode.JuniorRobot;

    public interface Strategist {
        void run(JuniorRobot robot);
        void onScannedRobot(JuniorRobot robot);
        void onHitByBullet(JuniorRobot robot);
        void onHitWall(JuniorRobot robot);
        void onHitRobot(JuniorRobot robot);
    }
