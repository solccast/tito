package jvolpecastro;
import robocode.JuniorRobot;

public abstract class Strategy {

        public abstract void run(JuniorRobot robot);

        public abstract void onScannedRobot(JuniorRobot robot);

        public abstract void onHitByBullet(JuniorRobot robot);

        public abstract void onHitRobot(JuniorRobot robot);

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
