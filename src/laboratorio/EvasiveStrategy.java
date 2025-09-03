package laboratorio;

import robocode.JuniorRobot;

public class EvasiveStrategy extends Strategy {


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
        if (robot.scannedDistance <= 15) { // Si el robot se encuentra cerca. Atacar.
            robot.fire(3);
        } else if (robot.scannedDistance <= 35) {
            robot.fire(1);
        } else {
            robot.turnRight(180);// Acá cambiaría a turnBack, para alejarse del enemigo
            robot.back(30);
        }
    }

    @Override
    public void onHitByBullet(JuniorRobot robot) {
        // Actitud reactiva ->  gira y ataca a quien lo atacó pero de soft
        int angleBullet = robot.hitByBulletAngle;
        robot.turnGunTo(angleBullet);
        robot.fire(1);
        //Escape
        int anguloEscape = (int) (Math.random() * 180) - 90;
        robot.turnBackRight(100, anguloEscape);
    }

    @Override
    public void onHitRobot(JuniorRobot robot) {
        robot.turnGunTo(robot.scannedAngle);
        robot.fire(1);
        robot.back(50);
        robot.turnRight(90);
        robot.ahead(120);
    }

}