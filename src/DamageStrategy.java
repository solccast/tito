package laboratorio;
import robocode.JuniorRobot;

public class DamageStrategy implements Strategy{

        @Override
        public void run(JuniorRobot robot) {
            //Movimiento inicial en búsqueda del enemigo
            while(true){
                // ZigZag
                robot.ahead(150);
                robot.turnRight(45);

                robot.ahead(100);
                robot.turnLeft(90);

                // Radar girando para detectar constantemente a los otros
                robot.turnGunRight(360); //Giro completo
            }

        }

        @Override
        public void onScannedRobot(JuniorRobot robot) {
            robot.turnGunTo(robot.scannedAngle);
            if (robot.scannedDistance < 15 && robot.energy > 30) { // Si el robot se encuentra cerca y hay energía suficiente. Atacar.
                robot.fire(3);
            } else if (robot.energy < 30 && robot.scannedDistance < 15){ // Si el robot posea ba

                robot.fire(1);
            } else robot.back(30); // Acá cambiaría de state

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
