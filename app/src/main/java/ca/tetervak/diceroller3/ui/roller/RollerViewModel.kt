package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.domain.Game
import ca.tetervak.diceroller3.domain.asRollData
import ca.tetervak.diceroller3.data.GameDataRepository

class RollerViewModel : ViewModel() {

    private val repository = GameDataRepository.getRepository()

    private val gameValue: Game = Game()
    private val _game: MutableLiveData<Game> = MutableLiveData(gameValue)
    val game: LiveData<Game> = _game

    fun roll() {
        gameValue.roll()
        _game.value = gameValue
    }

    fun reset() {
        gameValue.reset()
        _game.value = gameValue
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