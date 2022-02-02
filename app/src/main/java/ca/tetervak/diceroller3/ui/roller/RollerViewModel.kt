package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller3.model.Game
import ca.tetervak.diceroller3.model.asRollData
import ca.tetervak.diceroller3.data.GameDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    // this method has a bug
    fun save(): Boolean {
        return if (gameValue.isRolled) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.saveRoll(gameValue.asRollData())
            }
            true // <- this is a bug
        } else {
            false
        }
    }

}