package laboratorio;

import robocode.JuniorRobot;

public class AdaptiveStrategy implements Strategy {

    private int successfulHits = 0;
    private int receivedHits = 0;
    private int closeEncounters = 0;

    private boolean aggressive = true;

    public void run(JuniorRobot robot) {
        while (true) {
            evaluateMode();
            robot.turnGunRight(360); // Escaneo continuo

            if (aggressive) {
                robot.ahead(120);
                robot.turnRight(30);
            } else {
                robot.turnRight(60);
                robot.back(80);
            }
        }
    }

    public void onScannedRobot(JuniorRobot robot) {
        int distance = robot.scannedDistance;
        int angle = robot.scannedAngle;

        // Si el enemigo está cerca, cuenta como encuentro cercano
        if (distance < 150) {
            closeEncounters++;
        }

        robot.turnGunTo(angle);

        // Ajuste de potencia según distancia
        int power = (distance < 100) ? 3 : (distance < 300 ? 2 : 1);
        robot.fire(power);

        // Asumimos que si disparaste en rango corto, fue exitoso
        if (distance < 200) {
            successfulHits++;
        }

        // Movimiento evasivo ligero
        robot.turnRight(15);
        robot.ahead(50);
    }

    public void onHitByBullet(JuniorRobot robot) {
        receivedHits++;

        // Evasión perpendicular al disparo
        robot.turnRight(90 - robot.hitByBulletBearing);
        robot.ahead(120);
    }

    public void onHitRobot(JuniorRobot robot) {
        closeEncounters++;
        robot.turnGunTo(robot.hitRobotAngle);
        robot.fire(3);
        robot.back(100);
        robot.turnRight(45);
    }

    public void onHitWall(JuniorRobot robot) {
        robot.back(60);
        robot.turnRight(90);
    }

    // Simula "aprendizaje" al adaptar comportamiento
    private void evaluateMode() {
        // Lógica simple: si fallas mucho o te golpean más, defiende
        if (receivedHits > successfulHits + 3 || closeEncounters > 5) {
            aggressive = false;
        } else {
            aggressive = true;
        }

        // Reiniciar los contadores cada cierto tiempo
        if (receivedHits + successfulHits > 20) {
            receivedHits = 0;
            successfulHits = 0;
            closeEncounters = 0;
        }
    }
}

