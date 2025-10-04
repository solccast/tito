package jvolpecastro

import robocode.JuniorRobot

class Fiat100: JuniorRobot() {
    var estrategia: StrategyKotlin = TacheroDefensor()

    override fun run(){
        estrategia.run(this)
    }

    //Falta llamar al resto de métodos
    //Falta probar si funciona con el otro tipo de estrategia
    // y habría que cambiarle el nombre al file donde definí el strategy :( o reemplazarlo por otro más bonito xd
    // Eliminar las clases java
}