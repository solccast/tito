package prueba1;

import robocode.JuniorRobot;

public interface Strategy {

    void run(JuniorRobot robot);
    void onScannedRobot(JuniorRobot robot);
    void onHitByBullet(JuniorRobot robot);
    void onHitWall(JuniorRobot robot);
    void onHitRobot(JuniorRobot robot);
}
