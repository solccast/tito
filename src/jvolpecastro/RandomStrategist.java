package jvolpecastro;

import robocode.JuniorRobot;
import java.util.Random;

/*Estratega con 2 clases  las cuáles permuta de forma aleatoria*/
public class RandomStrategist implements IStrategist{

    private static final IStrategist INSTANCE = new RandomStrategist();
    private Strategy currentStrategy;
    private final Strategy strategy1 = new StrategyRandom1();
    private final Strategy strategy2 = new StrategyRandom2();
    private final Random random = new Random();

    private RandomStrategist() {
        changeStrategy();
    }

    public static IStrategist getInstance() {
        return INSTANCE;
    }
    /*Se establece estrategia aleatoria dependiendo el booleano random obtenido*/
    private void changeStrategy() {
        currentStrategy = random.nextBoolean() ? strategy1 : strategy2;
    }
    /* Primero cambia la estrategia y se llama el método run luego*/
    public void run(JuniorRobot robot) {
        changeStrategy();  // Cambia la estrategia en cada tick
        currentStrategy.run(robot);
    }

    public void onScannedRobot(JuniorRobot robot) {
        currentStrategy.onScannedRobot(robot);
    }

    public void onHitByBullet(JuniorRobot robot) {
        currentStrategy.onHitByBullet(robot);
    }

    public void onHitWall(JuniorRobot robot) {
        currentStrategy.onHitWall(robot);
    }

    public void onHitRobot(JuniorRobot robot) {
        currentStrategy.onHitRobot(robot);
    }

    /* Subestrategias que extienden la clase abstracta Strategy*/
    class StrategyRandom1 extends Strategy {
        @Override
        public void run(JuniorRobot robot) {
            robot.turnGunRight(360);
            robot.ahead(100);
        }

        @Override
        public void onScannedRobot(JuniorRobot robot) {
            robot.fire(1);
        }

        @Override
        public void onHitByBullet(JuniorRobot robot) {
            robot.back(50);
            robot.turnRight(45);
        }

        @Override
        public void onHitRobot(JuniorRobot robot) {
            robot.fire(1);
        }
    }

    class StrategyRandom2 extends Strategy {
        @Override
        public void run(JuniorRobot robot) {
            robot.turnRight(30);
            robot.ahead(50);
        }

        @Override
        public void onScannedRobot(JuniorRobot robot) {
            robot.fire(3);
        }

        @Override
        public void onHitByBullet(JuniorRobot robot) {
            robot.back(20);
        }

        @Override
        public void onHitRobot(JuniorRobot robot) {
            robot.turnLeft(30);
            robot.ahead(40);
        }
    }
}
