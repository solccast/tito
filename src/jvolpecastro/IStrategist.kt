package jvolpecastro

import robocode.JuniorRobot

interface IStrategist{
    abstract fun run (robot: JuniorRobot)
    abstract fun onScannedRobot(robot: JuniorRobot)
    abstract fun onHitByBullet(robot: JuniorRobot)
    abstract fun onHitRobot(robot: JuniorRobot)
    abstract fun onHitWall(robot: JuniorRobot)
}