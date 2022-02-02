package ca.tetervak.diceroller3.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller3.data.HistoryDataRepository
import ca.tetervak.diceroller3.model.HistoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {

    private val repository = HistoryDataRepository.getRepository()

    @ExperimentalCoroutinesApi
    val history: LiveData<HistoryData> =
        repository.getHistoryDataFlow().flowOn(Dispatchers.IO).asLiveData()

    fun deleteRoll(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRoll(id)
        }
    }

    fun clearAllRolls(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAllRolls()
        }
    }
}