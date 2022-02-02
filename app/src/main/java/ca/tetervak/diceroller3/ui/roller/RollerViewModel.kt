package ca.tetervak.diceroller3.ui.roller

import androidx.lifecycle.*
import androidx.lifecycle.Transformations.switchMap
import ca.tetervak.diceroller3.data.HistoryDataRepository
import ca.tetervak.diceroller3.data.RollDataRepository
import ca.tetervak.diceroller3.model.Game
import ca.tetervak.diceroller3.model.RollData
import ca.tetervak.diceroller3.model.asRollData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RollerViewModel : ViewModel() {

    private val historyDataRepository = HistoryDataRepository.getRepository()
    private val rollDataRepository = RollDataRepository.getRepository()

    private val isRolledValue = false
    private val _isRolled = MutableLiveData(isRolledValue)
    val isRolled: LiveData<Boolean> = _isRolled

    private val rollDataFromFlow =
        rollDataRepository.getRollDataFlow().flowOn(Dispatchers.IO).asLiveData()

    private val rollDataInit = MutableLiveData(Game().asRollData())

    val rollData: LiveData<RollData> = switchMap(isRolled){
        if(it){
            rollDataFromFlow
        }else{
            rollDataInit
        }
    }

    fun roll() {
        viewModelScope.launch(Dispatchers.IO){
            rollDataRepository.rollDice()
            _isRolled.postValue(true)
        }
    }

    fun reset() {
        _isRolled.value = false
    }

    // this method has a bug
    fun save(): Boolean {
//        return if (gameValue.isRolled) {
//            viewModelScope.launch(Dispatchers.IO) {
//                historyDataRepository.saveRoll(gameValue.asRollData())
//            }
//            true // <- this is a bug
//        } else {
//            false
//        }
        return true
    }

}