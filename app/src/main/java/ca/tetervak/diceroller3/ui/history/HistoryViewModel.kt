package ca.tetervak.diceroller3.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.diceroller3.data.GameDataRepository
import ca.tetervak.diceroller3.domain.HistoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {

    private val repository = GameDataRepository.getRepository()

    val historyData: LiveData<HistoryData> = repository.observeHistoryData()

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