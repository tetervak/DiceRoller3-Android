package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.domain.Game

class RollerViewModel: ViewModel() {

    private val game: Game = Game()

    fun roll(){
        game.roll()
    }

    fun reset(){
        game.reset()
    }

    fun save(){

    }

}