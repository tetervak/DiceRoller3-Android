package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller3.data.HistoryDataFlowRepository
import ca.tetervak.diceroller3.model.Game
import ca.tetervak.diceroller3.model.RollData
import ca.tetervak.diceroller3.model.asRollData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RollerViewModel : ViewModel() {

    private val repository = HistoryDataFlowRepository.getRepository()

    private val isRolledValue = false
    private val _isRolled = MutableLiveData(isRolledValue)
    val isRolled: LiveData<Boolean> = _isRolled

    private val gameValue: Game = Game()

    private val _rollData: MutableLiveData<RollData> = MutableLiveData(gameValue.asRollData())
    val rollData: LiveData<RollData> = _rollData

    fun roll() {
        gameValue.roll()
        _rollData.value = gameValue.asRollData()
        _isRolled.value = true
    }

    fun reset() {
        gameValue.reset()
        _rollData.value = gameValue.asRollData()
        _isRolled.value = false
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