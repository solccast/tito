package laboratorio;


import robocode.JuniorRobot;

public class basicStrategy implements Strategy {

    public void run(JuniorRobot robot) {
        while (true) {

            // Movimiento del Robot
            robot.turnLeft(90);
            robot.ahead(100);
            robot.turnRight(90);
            robot.ahead(100);
        }
    }

    public void onScannedRobot(JuniorRobot robot) {
        int scannedDistance = robot.scannedDistance;

        // Mover el radar solo si esta cerca
        if (scannedDistance < 500) {

            // Posición del Robot
            int posX = robot.robotX;
            int posY = robot.robotY;

            // Datos del robot escaneado
            int scannedAngle = robot.scannedAngle;
            int scannedHeading = robot.scannedHeading;
            int scannedVelocity = robot.scannedVelocity;

            // Cálculo de la posición del robot
            double enemyX = posX + scannedDistance * Math.sin(Math.toRadians(scannedAngle));
            double enemyY = posY + scannedDistance * Math.cos(Math.toRadians(scannedAngle));

            // Dar el valor al Poder dependiendo de la distancia
            int power = (scannedDistance < 100) ? 3 : 2;

            // Cálculo del tiempo
            double time = scannedDistance / (20 - 3 * power);

            // Cálculo de la posible posición del enemigo
            double futureX = enemyX + Math.sin(Math.toRadians(scannedHeading)) * scannedVelocity * time;
            double futureY = enemyY + Math.cos(Math.toRadians(scannedHeading)) * scannedVelocity * time;

            // Ángulo a disparar
            double angleToTarget = Math.toDegrees(Math.atan2(futureX - posX, futureY - posY));

            // Disparar solo si esta cerca
            if (scannedDistance < 200) {
                robot.turnGunTo((int) angleToTarget);
                robot.fire(power);
            }

            robot.turnGunTo(scannedAngle);
        }
    }

    public void onHitByBullet(JuniorRobot robot) {
        int angleBullet = robot.hitByBulletAngle;

        // Mover el cuerpo hacia la posición donde nos dispararon
        robot.turnTo(angleBullet);
        robot.turnGunTo(angleBullet);
    }

    public void onHitRobot(JuniorRobot robot) {
        int robotBearing = robot.hitRobotBearing;
        int robotAngle = robot.hitRobotAngle;

        // Mover el arma, disparar y moverse en cuanto al ángulo del robot que nos
        // golpeo
        robot.turnGunTo(robotAngle);
        robot.fire(3);
        if (robotBearing < 0) {
            robot.back(150);
            robot.turnRight(180);
        } else {
            robot.back(150);
            robot.turnLeft(180);
        }
    }

    public void onHitWall(JuniorRobot robot) {
        int wallBearing = robot.hitWallBearing;

        // Mover el robot en cuanto al ángulo donde golpeamos la pared
        if (wallBearing < 0) {
            robot.back(50);
            robot.turnRight(180);
        } else {
            robot.back(50);
            robot.turnLeft(180);
        }
    }

}