package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class RollDataRepository private constructor(){

    private val dataFlowSource = RollDataFlowSource()

    suspend fun rollDice() = dataFlowSource.rollDice()

    @ExperimentalCoroutinesApi
    fun getRollDataFlow(): Flow<RollData> = dataFlowSource.getRollDataFlow()

    companion object {

        @Volatile
        private var INSTANCE: RollDataRepository? = null

        fun getRepository(): RollDataRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RollDataRepository().also {
                    INSTANCE = it
                }
            }
        }
    }


}