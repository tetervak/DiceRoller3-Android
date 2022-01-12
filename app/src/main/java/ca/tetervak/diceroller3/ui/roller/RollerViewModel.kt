package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.domain.Game
import ca.tetervak.diceroller3.domain.asRollData
import ca.tetervak.diceroller3.data.GameDataRepository

class RollerViewModel : ViewModel() {

    private val repository = GameDataRepository.getRepository()

    val game: Game = Game()

    fun roll() {
        game.roll()
    }

    fun reset() {
        game.reset()
    }

    fun save(): Boolean {
        return if (game.isRolled) {
            repository.saveRoll(game.asRollData())
            true
        } else {
            false
        }
    }

}