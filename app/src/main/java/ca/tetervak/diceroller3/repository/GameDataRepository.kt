package ca.tetervak.diceroller3.repository

import ca.tetervak.diceroller3.domain.HistoryItem

class GameDataRepository private constructor() {

    private val items = ArrayList<HistoryItem>()

    fun save(item: HistoryItem){
        items.add(item)
        item.id = items.size
    }

    // it gives a copy of the stored list
    fun getHistory() = items.toList()

    fun clearAll(){
        items.clear()
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