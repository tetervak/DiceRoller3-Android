package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.domain.Game
import ca.tetervak.diceroller3.domain.asRollData
import ca.tetervak.diceroller3.data.GameDataRepository

class RollerViewModel : ViewModel() {

    private val repository = GameDataRepository.getRepository()

    val gameValue: Game = Game()

    fun roll() {
        gameValue.roll()
    }

    fun reset() {
        gameValue.reset()
    }

    fun save(): Boolean {
        return if (gameValue.isRolled) {
            repository.saveRoll(gameValue.asRollData())
            true
        } else {
            false
        }
    }

}