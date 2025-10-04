package jvolpecastro

import robocode.JuniorRobot

abstract class StrategyKotlin {
    abstract fun run (robot: JuniorRobot)
    abstract fun onScannedRobot(robot: JuniorRobot)
    abstract fun onHitByBullet(robot: JuniorRobot)
    abstract fun onHitRobot(robot: JuniorRobot)
    fun onHitWall(robot: JuniorRobot){
        // Una vez que choca contra la pared se distancia y el giro es aleatorio
        robot.back(50)
        if (Math.random() > 0.5) {
            robot.turnRight(90)
        } else {
            robot.turnLeft(90)
        }
        robot.ahead(100) //Se adelanta
        robot.turnGunRight(360) // Radar de búsqueda
    }
}

class TacheroDefensor : StrategyKotlin() {
    override fun run(robot: JuniorRobot) {
        robot.ahead(150)
        robot.turnRight(45)
        robot.ahead(100)
        robot.turnLeft(90)

        //Radar girando para detectar contrincantes
        robot.turnGunRight(360)
    }

    override fun onScannedRobot(robot: JuniorRobot) {
        robot.turnGunTo(robot.scannedAngle)
        if (robot.scannedAngle<15 && robot.energy >= 20){
            robot.fire(3.0)
        } else{
            robot.back(30)
        }
    }

    override fun onHitByBullet(robot: JuniorRobot) {
        // Actitud reactiva -> gira y ataca a quien lo atacó pero de soft
        val angleBullet = robot.hitByBulletAngle
        robot.turnTo(angleBullet)
        robot.turnGunTo(angleBullet)
        robot.fire(1.0)
    }

    override fun onHitRobot(robot: JuniorRobot) {
        robot.turnGunTo(robot.hitRobotAngle)
        robot.fire(3.0)
        robot.turnBackLeft(50, 45)
    }
}