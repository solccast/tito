package jvolpecastro

import robocode.JuniorRobot

class Fiat100 : JuniorRobot() {

    private val strategist: IStrategist = getTacheroStrategist() // getRandomStrategist()

    override fun run() {
        setColors(blue, blue, yellow, black, yellow)
        while (true) {
            strategist.run(this)
        }
    }

    override fun onScannedRobot() {
        strategist.onScannedRobot(this)
    }

    override fun onHitByBullet() {
        strategist.onHitByBullet(this)
    }

    override fun onHitWall() {
        strategist.onHitWall(this)
    }

    override fun onHitRobot() {
        strategist.onHitRobot(this)
    }
}
