package laboratorio;

import robocode.JuniorRobot;

interface Mode {
    Strategy checkMode(JuniorRobot r);
}