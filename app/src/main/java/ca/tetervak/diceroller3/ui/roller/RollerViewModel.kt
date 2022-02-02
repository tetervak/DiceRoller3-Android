package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.*
import ca.tetervak.diceroller3.data.HistoryDataRepository
import ca.tetervak.diceroller3.data.RollDataRepository
import ca.tetervak.diceroller3.model.Game
import ca.tetervak.diceroller3.model.RollData
import ca.tetervak.diceroller3.model.asRollData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RollerViewModel : ViewModel() {

    private val historyDataRepository = HistoryDataRepository.getRepository()
    private val rollDataRepository = RollDataRepository.getRepository()

    private val _isRolled = MutableLiveData(false)
    val isRolled: LiveData<Boolean> = _isRolled

    private val _rollData = MutableLiveData(Game().asRollData())
    val rollData: LiveData<RollData> = _rollData

    fun roll() {
        viewModelScope.launch(Dispatchers.IO) {
            val rollData = rollDataRepository.getRandomRollData()
            _rollData.postValue(rollData)
            _isRolled.postValue(true)
        }
    }

    fun reset() {
        _isRolled.value = false
    }

    // this method has a bug
    fun save(): Boolean {
        _isRolled.value?.let { rolled ->
            if (rolled) {
                _rollData.value?.let { roll ->
                    viewModelScope.launch(Dispatchers.IO) {
                        historyDataRepository.saveRoll(roll)
                    }
                    return true
                }
            }
        }
        return false
    }

}