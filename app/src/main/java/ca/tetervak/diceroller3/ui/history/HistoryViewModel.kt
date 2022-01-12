package ca.tetervak.diceroller3.ui.history

import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.data.GameDataRepository

class HistoryViewModel: ViewModel() {

    private val repository = GameDataRepository.getRepository()

    fun getHistoryData() = repository.getHistoryData()

    fun deleteRoll(id: Int){
        repository.deleteRoll(id)
    }

    fun clearAllRolls(){
        repository.clearAllRolls()
    }
}