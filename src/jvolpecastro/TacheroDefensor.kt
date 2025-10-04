package jvolpecastro

import robocode.JuniorRobot


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