package ca.tetervak.diceroller3.repository

import ca.tetervak.diceroller3.domain.HistoryItem

class GameDataRepository private constructor() {

    private val items = ArrayList<HistoryItem>()

    fun save(item: HistoryItem){
        item.id = items.size
        items.add(item)
    }

    fun getHistory() = items

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