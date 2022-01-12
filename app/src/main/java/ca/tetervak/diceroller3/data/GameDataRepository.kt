package ca.tetervak.diceroller3.data

import ca.tetervak.diceroller3.domain.HistoryData
import ca.tetervak.diceroller3.domain.RollData

class GameDataRepository private constructor() {

    private val dataSource = GameDataSource()

    fun saveRoll(rollData: RollData) {
        dataSource.saveRoll(rollData)
    }

    fun getHistoryData(): HistoryData {
        return dataSource.getHistoryData()
    }

    fun deleteRoll(id: Int) {
        dataSource.deleteRoll(id)
    }

    fun clearAllRolls() {
        dataSource.clearAllRolls()
    }

    companion object {

        @Volatile
        private var INSTANCE: GameDataRepository? = null

        fun getRepository(): GameDataRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: GameDataRepository().also {
                    INSTANCE = it
                }
            }
        }
    }
}