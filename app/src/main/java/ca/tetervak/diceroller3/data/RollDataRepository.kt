package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.delay

class RollDataRepository private constructor(){

    private val dataSource = RollDataSource()

    suspend fun getRandomRollData(): RollData {
        delay(100) // fake delay
        dataSource.rollDice()
        return dataSource.getRollData()
    }

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