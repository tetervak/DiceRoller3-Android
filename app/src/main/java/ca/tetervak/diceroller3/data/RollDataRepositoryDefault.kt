package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.model.RollData
import kotlinx.coroutines.delay

class RollDataRepositoryDefault private constructor() : RollDataRepository {

    private val dataSource = RollDataSource()

    override suspend fun getRandomRollData(): RollData {
        delay(100) // fake delay
        dataSource.rollDice()
        return dataSource.getRollData()
    }

    companion object {

        @Volatile
        private var INSTANCE: RollDataRepositoryDefault? = null

        fun getRepository(): RollDataRepositoryDefault {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RollDataRepositoryDefault().also {
                    INSTANCE = it
                }
            }
        }
    }


}