package jvolpecastro;
import robocode.*;

public class TacheroStrategist extends  Strategy{
    private final static Strategy INSTANCE = new TacheroStrategist();
    private Strategy currentStrategy;
    private final Strategy fullAtack = new FullAtack();
    private final Strategy Evasive = new Evasive();


    public TacheroStrategist(){}

    public static Strategy getInstance() {
        return INSTANCE;
    }

    /* ------ Estrategia 1: Ataque */
    class FullAtack extends Strategy{

        @Override
        public void run(JuniorRobot robot){
            // ZigZag
            robot.ahead(150);
            robot.turnRight(45);

            robot.ahead(100);
            robot.turnLeft(90);

            // Radar girando para detectar constantemente a los otros
            robot.turnGunRight(360); //Giro completo
        }

        @Override
        public void onScannedRobot(JuniorRobot robot) {
            robot.turnGunTo(robot.scannedAngle);
            if (robot.scannedDistance < 15 && robot.energy >= 20) { // Si el robot se encuentra cerca y hay energía suficiente. Atacar.
                robot.fire(3);
            } else{ robot.back(30);};
        }

        @Override
        public void onHitByBullet(JuniorRobot robot) {
            // Actitud reactiva -> gira y ataca a quien lo atacó pero de soft
            int angleBullet = robot.hitByBulletAngle;
            robot.turnTo(angleBullet);
            robot.turnGunTo(angleBullet);
            robot.fire(1);
        }

        @Override
        public void onHitRobot(JuniorRobot robot) {
            // Si se choca con un robot -> actitud reactiva y de escape.
            robot.turnGunTo(robot.hitRobotAngle);
            robot.fire(3);
            robot.turnBackLeft(50, 45);
        }
    }

    /* ----- Estrategia 2: Defense */
    class Evasive extends Strategy{

        @Override
        public void run(JuniorRobot robot) {

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

    public void run(JuniorRobot robot) {
        this.checkStatus(robot);
        currentStrategy.run(robot);
    }

    @Override
    public void onScannedRobot(JuniorRobot robot) { currentStrategy.onScannedRobot(robot);}

    @Override
    public void onHitByBullet(JuniorRobot robot) { currentStrategy.onHitByBullet(robot);}

    @Override
    public void onHitRobot(JuniorRobot robot) { currentStrategy.onHitRobot(robot);}

    public void checkStatus(JuniorRobot robot){
        //Condiciones
    }


}