package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.ExperimentalCoroutinesApi

class HistoryDataRepositoryDefault private constructor() : HistoryDataRepository {

    private val dataFlowSource = HistoryDataFlowSource()

    override suspend fun saveRoll(rollData: RollData) = dataFlowSource.saveRoll(rollData)

    override suspend fun deleteRoll(id: Int) = dataFlowSource.deleteRoll(id)

    override suspend fun clearAllRolls() = dataFlowSource.clearAllRolls()

    @ExperimentalCoroutinesApi
    override fun getHistoryDataFlow() = dataFlowSource.getHistoryDataFlow()

    companion object {

        @Volatile
        private var INSTANCE: HistoryDataRepositoryDefault? = null

        fun getRepository(): HistoryDataRepositoryDefault {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: HistoryDataRepositoryDefault().also {
                    INSTANCE = it
                }
            }
        }
    }

}