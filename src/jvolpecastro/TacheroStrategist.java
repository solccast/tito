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
            //Hace algo
        }

        @Override
        public void onScannedRobot(JuniorRobot robot) {

        }

        @Override
        public void onHitByBullet(JuniorRobot robot) {

        }

        @Override
        public void onHitRobot(JuniorRobot robot) {

        }
    }

    /* ----- Estrategia 2: Defense */
    class Evasive extends Strategy{

        @Override
        public void run(JuniorRobot robot) {

        }

        @Override
        public void onScannedRobot(JuniorRobot robot) {

        }

        @Override
        public void onHitByBullet(JuniorRobot robot) {

        }

        @Override
        public void onHitRobot(JuniorRobot robot) {

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