package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HistoryDataFlowRepository {

    private val dataFlowSource = HistoryDataFlowSource()

    suspend fun saveRoll(rollData: RollData) = dataFlowSource.saveRoll(rollData)

    suspend fun deleteRoll(id: Int) = dataFlowSource.deleteRoll(id)

    suspend fun clearAllRolls() = dataFlowSource.clearAllRolls()

    @ExperimentalCoroutinesApi
    fun getHistoryDataFlow() = dataFlowSource.getHistoryDataFlow()

    companion object {

        @Volatile
        private var INSTANCE: HistoryDataFlowRepository? = null

        fun getRepository(): HistoryDataFlowRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: HistoryDataFlowRepository().also {
                    INSTANCE = it
                }
            }
        }
    }

}