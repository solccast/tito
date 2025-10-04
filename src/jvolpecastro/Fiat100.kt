package jvolpecastro

import robocode.JuniorRobot

class Fiat100: JuniorRobot() {
    var estrategia: StrategyKotlin = TacheroDefensor()

    override fun run(){
        estrategia.run(this)
    }

    override fun onScannedRobot(){
        estrategia.onScannedRobot(this)
    }

    override fun onHitByBullet(){
        estrategia.onHitByBullet(this)
    }

    override fun onHitRobot(){
        estrategia.onHitRobot(this)
    }

    override fun onHitWall(){
        estrategia.onHitWall(this)
    }

}