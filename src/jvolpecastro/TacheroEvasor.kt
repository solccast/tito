package jvolpecastro

import robocode.JuniorRobot
import kotlin.random.Random

class TacheroEvasor : StrategyKotlin() {

    override fun run(robot: JuniorRobot) {
        robot.turnGunRight(360)
        robot.ahead(100)
        robot.back(100)
        robot.turnGunLeft(360)
        changeSenseZigZag(robot, 75, 45)
    }

    private fun changeSenseZigZag(robot: JuniorRobot, distance: Int, angle: Int) {
        robot.ahead(distance)
        robot.turnRight(angle)
        robot.ahead(distance)
        robot.turnLeft(angle)
        robot.ahead(distance)
    }

    override fun onScannedRobot(robot: JuniorRobot) {
        robot.turnGunTo(robot.scannedAngle)

        when {
            robot.scannedDistance <= 15 -> robot.fire(3.0)
            robot.scannedDistance <= 35 -> robot.fire(1.0)
            else -> {
                robot.turnRight(180)
                robot.back(30)
            }
        }
    }

    override fun onHitByBullet(robot: JuniorRobot) {
        val angleBullet = robot.hitByBulletAngle
        robot.turnGunTo(angleBullet)
        robot.fire(1.0)

        // Movimiento evasivo: giro aleatorio y retroceso
        val escapeAngle = Random.nextDouble(-90.0, 90.0)
        robot.turnBackRight(100, escapeAngle.toInt())
    }

    override fun onHitRobot(robot: JuniorRobot) {
        robot.turnGunTo(robot.scannedAngle)
        robot.fire(1.0)
        robot.back(50)
        robot.turnRight(90)
        robot.ahead(120)
    }
}
