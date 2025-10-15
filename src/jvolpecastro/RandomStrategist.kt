package jvolpecastro

import robocode.JuniorRobot
import java.util.Random

private val instance: RandomStrategist = RandomStrategist()
fun getRandomStrategist(): IStrategist { return instance }

private class RandomStrategist : IStrategist {

    private var currentStrategy: StrategyKotlin
    private val strategy1 = StrategyRandom1()
    private val strategy2 = StrategyRandom2()
    private val random = Random()

    init {
        currentStrategy = if (random.nextBoolean()) strategy1 else strategy2
    }

    private fun changeStrategy() {
        currentStrategy = if (random.nextBoolean()) strategy1 else strategy2
    }

    override fun run(robot: JuniorRobot) {
        changeStrategy()
        currentStrategy.run(robot)
    }

    override fun onScannedRobot(robot: JuniorRobot) {
        currentStrategy.onScannedRobot(robot)
    }

    override fun onHitByBullet(robot: JuniorRobot) {
        currentStrategy.onHitByBullet(robot)
    }

    override fun onHitWall(robot: JuniorRobot) {
        currentStrategy.onHitWall(robot)
    }

    override fun onHitRobot(robot: JuniorRobot) {
        currentStrategy.onHitRobot(robot)
    }

    // Subestrategias internas
    private class StrategyRandom1 : StrategyKotlin() {
        override fun run(robot: JuniorRobot) {
            robot.turnGunRight(360)
            robot.ahead(100)
        }

        override fun onScannedRobot(robot: JuniorRobot) {
            robot.fire(1.0)
        }

        override fun onHitByBullet(robot: JuniorRobot) {
            robot.back(50)
            robot.turnRight(45)
        }

        override fun onHitRobot(robot: JuniorRobot) {
            robot.fire(1.0)
        }
    }

    private class StrategyRandom2 : StrategyKotlin() {
        override fun run(robot: JuniorRobot) {
            robot.turnRight(30)
            robot.ahead(50)
        }

        override fun onScannedRobot(robot: JuniorRobot) {
            robot.fire(3.0)
        }

        override fun onHitByBullet(robot: JuniorRobot) {
            robot.back(20)
        }

        override fun onHitRobot(robot: JuniorRobot) {
            robot.turnLeft(30)
            robot.ahead(40)
        }
    }
}
