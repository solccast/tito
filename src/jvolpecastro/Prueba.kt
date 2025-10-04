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
        TODO("Not yet implemented")
        robot.ahead(150)
        robot.turnRight(45)
        robot.ahead(100)
        robot.turnLeft(90)

        //Radar girando para detectar contrincantes
        robot.turnGunRight(360)
    }

    override fun onScannedRobot(robot: JuniorRobot) {
        TODO("Not yet implemented")
    }

    override fun onHitByBullet(robot: JuniorRobot) {
        TODO("Not yet implemented")
    }

    override fun onHitRobot(robot: JuniorRobot) {
        TODO("Not yet implemented")
    }
}