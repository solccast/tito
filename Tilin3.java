package prueba1;

import robocode.JuniorRobot;

public class Tilin3 extends JuniorRobot {
    private Strategy strategy;

    public Tilin3(){
        this.strategy = new Ataque();
    }

    @Override
    public void run(){
        setColors(yellow, white, black, blue, yellow);
        this.strategy.run(this);
    }

    @Override
    public void onHitByBullet(){
        this.strategy.onHitByBullet(this);
    }

    @Override
    public void onScannedRobot(){
        this.strategy.onScannedRobot(this);
    }

    @Override
    public void onHitRobot(){
        this.strategy.onHitRobot(this);
    }

    @Override
    public void onHitWall(){
        this.strategy.onHitWall(this);
    }

}
