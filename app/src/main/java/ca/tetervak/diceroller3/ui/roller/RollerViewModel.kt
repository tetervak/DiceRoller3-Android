package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.domain.Game
import ca.tetervak.diceroller3.domain.asHistoryItem
import ca.tetervak.diceroller3.repository.GameDataRepository

class RollerViewModel : ViewModel() {

    val game: Game = Game()

    private val repository = GameDataRepository.getRepository()

    fun roll() {
        game.roll()
    }

    fun reset() {
        game.reset()
    }

    fun save(): Boolean {
        return if (game.isRolled) {
            repository.save(game.asHistoryItem())
            true
        } else {
            false
        }
    }

}