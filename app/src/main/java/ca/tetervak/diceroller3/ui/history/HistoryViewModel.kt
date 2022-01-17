package ca.tetervak.diceroller3.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.data.GameDataRepository
import ca.tetervak.diceroller3.domain.HistoryData

class HistoryViewModel: ViewModel() {

    private val repository = GameDataRepository.getRepository()

    val historyData: LiveData<HistoryData> = repository.observeHistoryData()

    fun deleteRoll(id: Int){
        repository.deleteRoll(id)
    }

    fun clearAllRolls(){
        repository.clearAllRolls()
    }
}