package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.Game
import ca.tetervak.diceroller3.model.RollData
import ca.tetervak.diceroller3.model.asRollData

class RollDataSource {

    private val game: Game = Game()

    @Synchronized
    fun rollDice(){
        game.roll()
    }

    fun getRollData(): RollData {
        synchronized(game){
            return game.asRollData()
        }
    }

}