package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.*
import ca.tetervak.diceroller3.R
import ca.tetervak.diceroller3.data.HistoryDataRepository
import ca.tetervak.diceroller3.data.RollDataRepository
import ca.tetervak.diceroller3.model.RollData
import ca.tetervak.diceroller3.ui.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RollerViewModel @Inject constructor(
    private val historyDataRepository: HistoryDataRepository,
    private val rollDataRepository: RollDataRepository
) : ViewModel() {

    private val _isRolled = MutableLiveData(false)
    val isRolled: LiveData<Boolean> = _isRolled

    private val initialRollData = RollData()
    private val _rollData = MutableLiveData(initialRollData)
    val rollData: LiveData<RollData> = _rollData

    private val _messageText = MutableLiveData<Event<Int>>()
    val messageText: LiveData<Event<Int>> = _messageText

    private fun postMessage(message: Int){
        _messageText.postValue(Event(message))
    }

    fun roll() {
        viewModelScope.launch(Dispatchers.IO) {
            postMessage(R.string.rolling_the_dice)
            //delay(1000) // fake delay
            val rollData = rollDataRepository.getRandomRollData()
            historyDataRepository.saveRoll(rollData)
            //postMessage(R.string.data_is_saved)
            _rollData.postValue(rollData)
            _isRolled.postValue(true)
        }
    }

    fun reset() {
        _isRolled.value = false
        _rollData.value = initialRollData
    }

}