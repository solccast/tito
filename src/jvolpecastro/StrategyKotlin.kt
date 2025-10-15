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