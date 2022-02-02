package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HistoryDataRepository private constructor() {

    private val dataFlowSource = HistoryDataFlowSource()

    suspend fun saveRoll(rollData: RollData) = dataFlowSource.saveRoll(rollData)

    suspend fun deleteRoll(id: Int) = dataFlowSource.deleteRoll(id)

    suspend fun clearAllRolls() = dataFlowSource.clearAllRolls()

    @ExperimentalCoroutinesApi
    fun getHistoryDataFlow() = dataFlowSource.getHistoryDataFlow()

    companion object {

        @Volatile
        private var INSTANCE: HistoryDataRepository? = null

        fun getRepository(): HistoryDataRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: HistoryDataRepository().also {
                    INSTANCE = it
                }
            }
        }
    }

}