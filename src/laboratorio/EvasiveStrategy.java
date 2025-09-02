package laboratorio;

import robocode.JuniorRobot;

public class EvasiveStrategy implements Strategy {

    @Override
    public void run(JuniorRobot r) {
        r.turnGunRight(360);
        r.ahead(100);
        r.back(100);
        r.turnGunLeft(360);
        this.changeSenseZigZag(r, 75, 45);
    }

    private void changeSenseZigZag(JuniorRobot r, int distance, int angle) {
        r.ahead(distance);

        r.turnRight(angle);
        r.ahead(distance);

        r.turnLeft(angle);
        r.ahead(distance);
    }

    @Override
    public void onScannedRobot(JuniorRobot robot) {
        robot.turnGunTo(robot.scannedAngle);
        if (robot.scannedDistance < 15 && robot.energy > 30) { // Si el robot se encuentra cerca y hay energía suficiente. Atacar.
            robot.fire(3);
        } else if (robot.energy < 30 && robot.scannedDistance < 15){ // Si el robot posea ba

            robot.fire(1);
        } else robot.back(30);
    }

    @Override
    public void onHitByBullet(JuniorRobot robot) {
        // Actitud reactiva ->  gira y ataca a quien lo atacó pero de soft
        int angleBullet = robot.hitByBulletAngle;
        robot.turnTo(angleBullet);
        robot.turnGunTo(angleBullet);
        robot.fire(1);
    }

    @Override
    public void onHitRobot(JuniorRobot robot){
        // Si se choca con un robot -> actitud reactiva y de escape.
        robot.turnGunTo(robot.hitRobotAngle);
        robot.fire(3);
        robot.turnBackLeft(50, 45);
    }

    @Override
    public void onHitWall(JuniorRobot robot){
        // Una vez que choca contra la pared se distancia y el giro es aleatorio
        robot.back(50);
        if (Math.random() > 0.5){
            robot.turnRight(90);
        } else {
            robot.turnLeft(90);
        }

        robot.ahead(100); //Se adelanta
        robot.turnGunRight(360); // Radar de búsqueda
    }
}
