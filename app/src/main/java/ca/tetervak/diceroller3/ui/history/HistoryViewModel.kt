package ca.tetervak.diceroller3.ui.history

import androidx.lifecycle.ViewModel
import ca.tetervak.diceroller3.repository.GameDataRepository

class HistoryViewModel: ViewModel() {

    private val repository = GameDataRepository.getRepository()

    fun getHistory() = repository.getHistory()
}