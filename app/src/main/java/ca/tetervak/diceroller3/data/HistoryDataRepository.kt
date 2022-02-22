package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.HistoryData
import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

interface HistoryDataRepository {
    suspend fun saveRoll(rollData: RollData)

    suspend fun deleteRoll(id: Int)

    suspend fun clearAllRolls()

    @ExperimentalCoroutinesApi
    fun getHistoryDataFlow(): Flow<HistoryData>
}