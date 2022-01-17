package ca.tetervak.diceroller3.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ca.tetervak.diceroller3.domain.HistoryData
import ca.tetervak.diceroller3.domain.RollData

class GameDataRepository private constructor() {

    private val dataSource = GameDataSource()

    private var isObserved = false;
    inner class HistoryMutableLiveData(
        historyData: HistoryData
    ): MutableLiveData<HistoryData>(historyData){

        override fun onActive() {
            super.onActive()
            isObserved = true
            refresh()
        }

        override fun onInactive() {
            super.onInactive()
            isObserved = false
        }
    }

    private val historyData: HistoryMutableLiveData =
        HistoryMutableLiveData(dataSource.getHistoryData())

    private fun refresh(){
        historyData.value = getHistoryData()
    }

    fun saveRoll(rollData: RollData) {
        dataSource.saveRoll(rollData)
        if(isObserved){
            refresh()
        }
    }

    private fun getHistoryData(): HistoryData {
        return dataSource.getHistoryData()
    }

    fun observeHistoryData(): LiveData<HistoryData> = historyData

    fun deleteRoll(id: Int) {
        dataSource.deleteRoll(id)
        if(isObserved){
            refresh()
        }
    }

    fun clearAllRolls() {
        dataSource.clearAllRolls()
        if(isObserved){
            refresh()
        }
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