package jvolpecastro

import robocode.JuniorRobot
import kotlin.random.Random

private val instance: TacheroStrategist = TacheroStrategist()
fun getTacheroStrategist(): IStrategist { return instance }

private class TacheroStrategist : IStrategist {
        private val fullAttack = FullAttack()
        private val evasive = Evasive()

        private var currentStrategy: StrategyKotlin = fullAttack  // Valor inicial por defecto

        override fun run(robot: JuniorRobot) {
            checkStatus(robot)
            currentStrategy.run(robot)
        }

        override fun onScannedRobot(robot: JuniorRobot) {
            currentStrategy.onScannedRobot(robot)
        }

        override fun onHitByBullet(robot: JuniorRobot) {
            currentStrategy.onHitByBullet(robot)
        }

        override fun onHitRobot(robot: JuniorRobot) {
            currentStrategy.onHitRobot(robot)
        }

        override fun onHitWall(robot: JuniorRobot) {
            currentStrategy.onHitWall(robot)
        }

        private fun checkStatus(robot: JuniorRobot) {
            currentStrategy = if (robot.energy > 50) {
                fullAttack
            } else {
                evasive
            }
        }

        // ------------------------
        // Estrategia FullAttack
        // ------------------------
        private class FullAttack : StrategyKotlin() {
            override fun run(robot: JuniorRobot) {
                robot.ahead(15)
                robot.turnRight(45)

                robot.ahead(10)
                robot.turnLeft(9)

                robot.turnGunRight(36)
            }

            override fun onScannedRobot(robot: JuniorRobot) {
                robot.turnGunTo(robot.scannedAngle)
                if (robot.scannedDistance < 15 && robot.energy >= 20) {
                    robot.fire(3.0)
                } else {
                    robot.back(3)
                }
            }

            override fun onHitByBullet(robot: JuniorRobot) {
                val angle = robot.hitByBulletAngle
                robot.turnTo(angle)
                robot.turnGunTo(angle)
                robot.fire(1.0)
            }

            override fun onHitRobot(robot: JuniorRobot) {
                robot.turnGunTo(robot.hitRobotAngle)
                robot.fire(3.0)
                robot.turnBackLeft(50, 45)
            }
        }

        // ------------------------
        // Estrategia Evasive
        // ------------------------
        private class Evasive : StrategyKotlin() {
            override fun run(robot: JuniorRobot) {
                robot.turnGunRight(36)
                robot.ahead(10)
                robot.back(10)
                robot.turnGunLeft(36)
                zigzagMove(robot)
            }

            private fun zigzagMove(r: JuniorRobot) {
                r.ahead(75)
                r.turnRight(45)
                r.ahead(75)
                r.turnLeft(45)
                r.ahead(75)
            }

            override fun onScannedRobot(robot: JuniorRobot) {
                robot.turnGunTo(robot.scannedAngle)
                when {
                    robot.scannedDistance <= 15 -> robot.fire(3.0)
                    robot.scannedDistance <= 35 -> robot.fire(1.0)
                    else -> {
                        robot.turnRight(18)
                        robot.back(3)
                    }
                }
            }

            override fun onHitByBullet(robot: JuniorRobot) {
                val angle = robot.hitByBulletAngle
                robot.turnGunTo(angle)
                robot.fire(1.0)

                val escapeAngle = Random.nextInt(-90, 90)
                robot.turnBackRight(100, escapeAngle.toInt())
            }

            override fun onHitRobot(robot: JuniorRobot) {
                robot.turnGunTo(robot.scannedAngle)
                robot.fire(1.0)
                robot.back(5)
                robot.turnRight(9)
                robot.ahead(12)
            }
        }
    }
